package com.example.leonanta.finalproject.mvp.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.R.id.*
import com.example.leonanta.finalproject.mvp.favorite.FavoriteFragment
import com.example.leonanta.finalproject.mvp.last.LastFragment
import com.example.leonanta.finalproject.mvp.next.NextFragment
import com.example.leonanta.finalproject.mvp.team.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_botNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                bnav_team -> {
                    loadTeamFragment(savedInstanceState)
                }

                bnav_last -> {
                    loadLastFragment(savedInstanceState)
                }

                bnav_next -> {
                    loadNextFragment(savedInstanceState)
                }

                bnav_fav -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        main_botNav.selectedItemId = bnav_team
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadLastFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, LastFragment(), LastFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextFragment(), NextFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }
}
