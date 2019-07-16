package com.example.leonanta.finalproject.mvp.allplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.adapter.AllPlayerAdapter
import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.model.AllPlayer
import com.example.leonanta.finalproject.model.AllTeam
import com.example.leonanta.finalproject.mvp.detail.*
import com.example.leonanta.finalproject.utils.invisible
import com.example.leonanta.finalproject.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.toast

const val DETAILPLAYER = "DETAILPLAYER"


class AllPlayerActivity : AppCompatActivity(), AllPlayerView {

    private var allPlayer: MutableList<AllPlayer> = mutableListOf()
    private lateinit var adapter: AllPlayerAdapter
    private lateinit var presenter: AllPlayerPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    lateinit var idTeam: String
    lateinit var teamName: String

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showNoData() {
        toast("No Data Available")
    }

    override fun showAllPlayer(data: List<AllPlayer>) {
        showPlayerList(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_player)

        val item = intent.getParcelableExtra<AllTeam>(DETAILPLAYER)
        idTeam = item.idTeam.toString()
        teamName = item.strTeam.toString()

        progressBar = findViewById(R.id.all_player_progBar)
        recyclerView = findViewById(R.id.all_player_recyclerView)
        val teamBadge = findViewById<ImageView>(R.id.all_player_badge)
        val teamName = findViewById<TextView>(R.id.all_player_team)

        Picasso.get().load(item.strTeamBadge).into(teamBadge)
        teamName.text = item.strTeam

        val request = APIRepository()
        val gson = Gson()
        presenter = AllPlayerPresenter(this, request, gson)
        adapter = AllPlayerAdapter(allPlayer) { item: AllPlayer -> clicked(item) }
        recyclerView.adapter = adapter

        presenter.getAllPlayer(idTeam)

        recyclerView.layoutManager = LinearLayoutManager(ctx)

    }

    private fun showPlayerList(data: List<AllPlayer>) {
        allPlayer.clear()
        allPlayer.addAll(data)
        adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(0)
    }

    private fun clicked(item: AllPlayer) {
        ctx.startActivity<DetailPlayerActivity>(LOOKUPPLAYER to item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_view, menu)

        val searchMenu = menu?.findItem(R.id.searchView)
        val searchView = searchMenu?.actionView as SearchView

        listenSearchView(searchView)
        return true
    }

    private fun listenSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getPlayerSearch(teamName, query.toString())

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                when (newText.toString()) {
                    "" -> presenter.getAllPlayer(idTeam)
                }
                return true
            }
        })
    }
}
