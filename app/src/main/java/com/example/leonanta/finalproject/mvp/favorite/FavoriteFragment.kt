package com.example.leonanta.finalproject.mvp.favorite


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.adapter.FavoriteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_favorite, main_container, false)

        val viewPager = fragmentView.findViewById<ViewPager>(R.id.fav_viewPager)
        val tabLayout = fragmentView.findViewById<TabLayout>(R.id.fav_tabLayout)
        val adapter = FavoriteAdapter(childFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        return fragmentView
    }


}
