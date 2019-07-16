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
import com.example.leonanta.finalproject.mvp.allplayer.AllPlayerPresenter
import com.example.leonanta.finalproject.mvp.allplayer.AllPlayerView
import com.example.leonanta.finalproject.mvp.allplayer.DETAILPLAYER
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

const val LOOKUPPLAYER = "LOOKUPPLAYER"

class DetailPlayerActivity : AppCompatActivity() {



    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var playerID: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        supportActionBar?.title = "Player Detail"

        val item = intent.getParcelableExtra<AllPlayer>(LOOKUPPLAYER)
        mainLayout(item)

        playerID = "${item.idPlayer}"

        favoriteState()



    }

    private fun mainLayout(item: AllPlayer) {
        val playerFanart = findViewById<ImageView>(R.id.detail_player_fanart)
        val playerName = findViewById<TextView>(R.id.detail_player_name)
        val playerWeight = findViewById<TextView>(R.id.detail_player_weight)
        val playerHeight = findViewById<TextView>(R.id.detail_player_height)
        val playerPosition = findViewById<TextView>(R.id.detail_player_position)
        val playerDescription = findViewById<TextView>(R.id.detail_player_description)

        Picasso.get().load(item.strFanart1).into(playerFanart)
        playerName.text = item.strPlayer
        playerWeight.text = item.strWeight
        playerHeight.text = item.strHeight
        playerPosition.text = item.strPosition
        playerDescription.text = item.strDescriptionEN


    }



    private fun favoriteState() {
        DB.use {
            val result = select(AllPlayer.FAVORITE_PLAYER)
                    .whereArgs(AllPlayer.PLAYER_ID + "={id}", "id" to playerID)

            val favorite = result.parseList(classParser<AllPlayer>())

            if (!favorite.isEmpty()) {
                isFavorite = true
            }
        }
    }

    private fun addToFavorite() {
        val item = intent.getParcelableExtra<AllPlayer>(LOOKUPPLAYER)
        try {
            DB.use {
                insert(AllPlayer.FAVORITE_PLAYER,
                        AllPlayer.PLAYER_ID to item.idPlayer,
                        AllPlayer.TEAM_ID to item.idTeam,
                        AllPlayer.PLAYER to item.strPlayer,
                        AllPlayer.TEAM to item.strTeam,
                        AllPlayer.PLAYER_DESCRIPTION to item.strDescriptionEN,
                        AllPlayer.POSITION to item.strPosition,
                        AllPlayer.HEIGHT to item.strHeight,
                        AllPlayer.WEIGHT to item.strWeight,
                        AllPlayer.CUTOUT to item.strCutout,
                        AllPlayer.FANART to item.strFanart1
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
                delete(AllPlayer.FAVORITE_PLAYER,
                        AllPlayer.PLAYER_ID + "={id}", "id" to playerID)
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
