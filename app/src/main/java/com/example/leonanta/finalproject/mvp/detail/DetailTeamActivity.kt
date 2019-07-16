package com.example.leonanta.finalproject.mvp.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.adapter.AllPlayerAdapter
import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.model.AllPlayer
import com.example.leonanta.finalproject.model.AllTeam
import com.example.leonanta.finalproject.model.DB
import com.example.leonanta.finalproject.model.MatchEvent
import com.example.leonanta.finalproject.mvp.allplayer.AllPlayerPresenter
import com.example.leonanta.finalproject.mvp.allplayer.AllPlayerView
import com.example.leonanta.finalproject.mvp.allplayer.DETAILPLAYER
import com.example.leonanta.finalproject.mvp.second.SecondPresenter
import com.example.leonanta.finalproject.mvp.team.TeamPresenter
import com.example.leonanta.finalproject.mvp.team.TeamView
import com.example.leonanta.finalproject.utils.invisible
import com.example.leonanta.finalproject.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

const val DETAILTEAM = "DETAILTEAM"

class DetailTeamActivity : AppCompatActivity(), AllPlayerView {

    private var allPlayer: MutableList<AllPlayer> = mutableListOf()
    private lateinit var adapter: AllPlayerAdapter
    private lateinit var presenter: AllPlayerPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    lateinit var idTeam: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var teamID: String

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

    private fun showPlayerList(data: List<AllPlayer>) {
        allPlayer.clear()
        allPlayer.addAll(data)
        adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        supportActionBar?.title = "Team Detail"

        val item = intent.getParcelableExtra<AllTeam>(DETAILTEAM)
        mainLayout(item)

        teamID = "${item.idTeam}"

        favoriteState()
    }

    private fun mainLayout(item: AllTeam){
        val teamName = findViewById<TextView>(R.id.detail_team_name)
        val teamBadge = findViewById<ImageView>(R.id.detail_team_badge)
        val teamStadium = findViewById<TextView>(R.id.detail_team_stadium)
        val teamYear = findViewById<TextView>(R.id.detail_team_year)
        val teamDescription = findViewById<TextView>(R.id.detail_team_description)

        teamName.text = item.strTeam
        Picasso.get().load(item.strTeamBadge).into(teamBadge)
        teamYear.text = item.intFormedYear
        teamStadium.text = item.strStadium
        teamDescription.text = item.strDescriptionEN

        idTeam = item.idTeam.toString()

        progressBar = findViewById(R.id.detail_team_progBar)
        recyclerView = findViewById(R.id.team_detail_recyclerview)


        val request = APIRepository()
        val gson = Gson()
        presenter = AllPlayerPresenter(this, request, gson)
        adapter = AllPlayerAdapter(allPlayer) { item: AllPlayer -> clicked(item) }
        recyclerView.adapter = adapter

        presenter.getAllPlayer(idTeam)

        recyclerView.layoutManager = LinearLayoutManager(ctx)
    }

    private fun clicked(item: AllPlayer) {
        ctx.startActivity<DetailPlayerActivity>(LOOKUPPLAYER to item)
    }

    private fun favoriteState() {
        DB.use {
            val result = select(AllTeam.FAVORITE_TEAM)
                    .whereArgs(AllTeam.TEAM_ID + "={id}", "id" to teamID)

            val favorite = result.parseList(classParser<AllTeam>())

            if(!favorite.isEmpty()){
                isFavorite = true
            }
        }
    }

    private fun addToFavorite() {
        val item = intent.getParcelableExtra<AllTeam>(DETAILTEAM)
        try {
            DB.use {
                insert(AllTeam.FAVORITE_TEAM,
                        AllTeam.TEAM_ID to item.idTeam,
                        AllTeam.TEAM to item.strTeam,
                        AllTeam.FORMED_YEAR to item.intFormedYear,
                        AllTeam.STADIUM to item.strStadium,
                        AllTeam.TEAM_DESCRIPTION to item.strDescriptionEN,
                        AllTeam.TEAM_BADGE to item.strTeamBadge
                )
                toast("Added To Favorite")
            }

        } catch (e: SQLiteConstraintException) {
            toast("Error ${e.message}")
        }
    }

    private fun removeFromFavorite() {
        try {
            DB.use {
                delete(AllTeam.FAVORITE_TEAM,
                        AllTeam.TEAM_ID + "={id}", "id" to teamID)
                toast("Removed From Favorite")
            }
        } catch (e: SQLiteConstraintException) {
            toast("Error ${e.message}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.second_menu, menu)

        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.addToFav -> {
                if (isFavorite) {
                    removeFromFavorite()
                } else {
                    addToFavorite()
                }

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
        }
    }
}
