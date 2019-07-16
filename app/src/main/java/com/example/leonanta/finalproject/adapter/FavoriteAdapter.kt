package com.example.leonanta.finalproject.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.leonanta.finalproject.mvp.favorite.FavMatchFragment
import com.example.leonanta.finalproject.mvp.favorite.FavPlayerFragment
import com.example.leonanta.finalproject.mvp.favorite.FavTeamFragment

class FavoriteAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val page = listOf(
            FavTeamFragment(),
            FavPlayerFragment(),
            FavMatchFragment()
    )

    override fun getItem(position: Int): Fragment {
        return page[position]
    }

    override fun getCount(): Int {
        return page.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Favorite Team"
            1 -> "Favorite Player"
            else -> "Favorite Match"
        }
    }
}