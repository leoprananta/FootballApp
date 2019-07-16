package com.example.leonanta.finalproject.mvp.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.adapter.TeamAdapter
import com.example.leonanta.finalproject.model.AllTeam
import com.example.leonanta.finalproject.model.DB
import com.example.leonanta.finalproject.model.MatchEvent
import com.example.leonanta.finalproject.mvp.detail.DETAILTEAM
import com.example.leonanta.finalproject.mvp.detail.DetailTeamActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class FavTeamFragment : Fragment() {

    private var allTeam: MutableList<AllTeam> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_fav_team, main_container, false)

        recyclerView = fragmentView.findViewById(R.id.fav_team_recyclerView)

        adapter = TeamAdapter(allTeam) { item: AllTeam -> clicked(item) }
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(ctx)

        allTeam.clear()
        showFavSubject()
        return fragmentView
    }

    private fun clicked(item: AllTeam) {
        ctx.startActivity<DetailTeamActivity>(DETAILTEAM to item)
    }

    private fun showFavSubject() {
        context?.DB?.use {
            val result = select(AllTeam.FAVORITE_TEAM)

            val favorite = result.parseList(classParser<AllTeam>())

            allTeam.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        allTeam.clear()
        showFavSubject()
        super.onResume()
    }
}
