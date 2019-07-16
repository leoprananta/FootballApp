package com.example.leonanta.finalproject.mvp.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.adapter.AllPlayerAdapter
import com.example.leonanta.finalproject.model.AllPlayer
import com.example.leonanta.finalproject.model.DB
import com.example.leonanta.finalproject.mvp.detail.DetailPlayerActivity
import com.example.leonanta.finalproject.mvp.detail.LOOKUPPLAYER
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class FavPlayerFragment : Fragment() {

    private var allPlayer: MutableList<AllPlayer> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllPlayerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_fav_player, main_container, false)

        recyclerView = fragmentView.findViewById(R.id.fav_player_recyclerView)

        adapter = AllPlayerAdapter(allPlayer) { item: AllPlayer -> clicked(item) }
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(ctx)

        allPlayer.clear()
        showFavSubject()
        return fragmentView
    }

    private fun clicked(item: AllPlayer) {
        ctx.startActivity<DetailPlayerActivity>(LOOKUPPLAYER to item)
    }

    private fun showFavSubject() {
        context?.DB?.use {
            val result = select(AllPlayer.FAVORITE_PLAYER)

            val favorite = result.parseList(classParser<AllPlayer>())

            allPlayer.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        allPlayer.clear()
        showFavSubject()
        super.onResume()
    }
}
