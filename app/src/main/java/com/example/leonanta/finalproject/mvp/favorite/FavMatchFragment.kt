package com.example.leonanta.finalproject.mvp.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.adapter.MatchAdapter
import com.example.leonanta.finalproject.model.MatchEvent
import com.example.leonanta.finalproject.model.DB
import com.example.leonanta.finalproject.mvp.second.DETAIL
import com.example.leonanta.finalproject.mvp.second.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class FavMatchFragment : Fragment() {

    private var matchEvent: MutableList<MatchEvent> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_fav_match, main_container, false)

        recyclerView = fragmentView.findViewById(R.id.fav_match_recyclerView)

        adapter = MatchAdapter(matchEvent) { item: MatchEvent -> clicked(item) }
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(ctx)

        matchEvent.clear()
        showFavSubject()
        return fragmentView
    }

    private fun clicked(item: MatchEvent) {
        ctx.startActivity<SecondActivity>(DETAIL to item)
    }

    private fun showFavSubject() {
        context?.DB?.use {
            val result = select(MatchEvent.FAVORITE_MATCH)
            val favorite = result.parseList(classParser<MatchEvent>())
            matchEvent.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        matchEvent.clear()
        showFavSubject()
        super.onResume()
    }
}
