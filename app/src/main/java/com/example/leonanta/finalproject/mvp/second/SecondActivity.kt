package com.example.leonanta.finalproject.mvp.second

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import com.example.leonanta.finalproject.R
import com.example.leonanta.finalproject.R.id.*
import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.model.MatchEvent
import com.example.leonanta.finalproject.model.TeamDetail
import com.example.leonanta.finalproject.model.DB
import com.example.leonanta.finalproject.utils.Date
import com.example.leonanta.finalproject.utils.invisible
import com.example.leonanta.finalproject.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

const val DETAIL = "DETAIL"

class SecondActivity : AppCompatActivity(), SecondView {

    private lateinit var presenter: SecondPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var scrollView: ScrollView
    private lateinit var homeBadge: ImageView
    private lateinit var awayBadge: ImageView

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var eventID: String

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showNoData() {
        toast("No Data Available, Cek Your Code")
    }

    override fun showTeamDetail(dataHome: List<TeamDetail>, dataAway: List<TeamDetail>) {
        Picasso.get()
                .load(dataHome[0].strTeamBadge)
                .resize(150, 150)
                .into(homeBadge)

        Picasso.get()
                .load(dataAway[0].strTeamBadge)
                .resize(150, 150)
                .into(awayBadge)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.title = "Match Detail"
        val item = intent.getParcelableExtra<MatchEvent>(DETAIL)
        mainLayout(item)
        generateData(item)

        eventID = "${item.idEvent}"

        favoriteState()
    }

    private fun mainLayout(item: MatchEvent) {
        val date = findViewById<TextView>(R.id.second_date)
        val time = findViewById<TextView>(R.id.second_time)
        homeBadge = findViewById(second_homeBadge)
        awayBadge = findViewById(second_awayBadge)
        val homeTeam = findViewById<TextView>(second_homeTeam)
        val awayTeam = findViewById<TextView>(second_awayTeam)
        val homeScore = findViewById<TextView>(second_homeScore)
        val awayScore = findViewById<TextView>(second_awayScore)
        val homeGoal = findViewById<TextView>(second_homeGoal)
        val awayGoal = findViewById<TextView>(second_awayGoal)
        val homeShot = findViewById<TextView>(second_homeShot)
        val awayShot = findViewById<TextView>(second_awayShot)
        val homeGoalkeeper = findViewById<TextView>(second_homeGoalkeeper)
        val awayGoalkeeper = findViewById<TextView>(second_awayGoalkeeper)
        val homeDefense = findViewById<TextView>(second_homeDefense)
        val awayDefense = findViewById<TextView>(second_awayDefense)
        val homeMidfield = findViewById<TextView>(second_homeMidfield)
        val awayMidfield = findViewById<TextView>(second_awayMidfield)
        val homeForward = findViewById<TextView>(second_homeForward)
        val awayForward = findViewById<TextView>(second_awayForward)
        val homeSubstitutes = findViewById<TextView>(second_homeSubstitutes)
        val awaySubstitutes = findViewById<TextView>(second_awaySubstitutes)
        scrollView = findViewById(second_scrollView)
        progressBar = findViewById(second_progBar)

        date.text = item.dateEvent?.let { Date.getDate(it) }
        time.text = item.strTime?.let { Date.getTime(it) }
        homeTeam.text = item.strHomeTeam
        awayTeam.text = item.strAwayTeam
        homeScore.text = item.intHomeScore
        awayScore.text = item.intAwayScore
        homeGoal.text = item.strHomeGoalDetails
        awayGoal.text = item.strAwayGoalDetails
        homeShot.text = item.intHomeShots
        awayShot.text = item.intAwayShots
        homeGoalkeeper.text = item.strHomeLineupGoalkeeper
        awayGoalkeeper.text = item.strAwayLineupGoalkeeper
        homeDefense.text = item.strHomeLineupDefense
        awayDefense.text = item.strAwayLineupDefense
        homeMidfield.text = item.strHomeLineupMidfield
        awayMidfield.text = item.strAwayLineupMidfield
        homeForward.text = item.strHomeLineupForward
        awayForward.text = item.strAwayLineupForward
        homeSubstitutes.text = item.strHomeLineupSubstitutes
        awaySubstitutes.text = item.strAwayLineupSubstitutes
    }

    private fun generateData(item: MatchEvent) {
        val request = APIRepository()
        val gson = Gson()
        presenter = SecondPresenter(this, request, gson)
        presenter.getTeamDetail(item.idHomeTeam!!, item.idAwayTeam!!)
    }

    private fun favoriteState() {
        DB.use {
            val result = select(MatchEvent.FAVORITE_MATCH)
                    .whereArgs(MatchEvent.ID_EVENT + "={id}", "id" to eventID)

            val favorite = result.parseList(classParser<MatchEvent>())

            if (!favorite.isEmpty()) {
                isFavorite = true
            }
        }
    }

    private fun addToFavorite() {
        val item = intent.getParcelableExtra<MatchEvent>(DETAIL)
        try {
            DB.use {
                insert(MatchEvent.FAVORITE_MATCH,
                        MatchEvent.ID_EVENT to item.idEvent,
                        MatchEvent.DATE to item.dateEvent,
                        MatchEvent.TIME to item.strTime,

                        MatchEvent.HOME_ID to item.idHomeTeam,
                        MatchEvent.HOME_TEAM to item.strHomeTeam,
                        MatchEvent.HOME_SCORE to item.intHomeScore,
                        MatchEvent.HOME_GOAL_DETAILS to item.strHomeGoalDetails,
                        MatchEvent.HOME_SHOTS to item.intHomeShots,
                        MatchEvent.HOME_LINEUP_GOALKEEPER to item.strHomeLineupGoalkeeper,
                        MatchEvent.HOME_LINEUP_DEFENSE to item.strHomeLineupDefense,
                        MatchEvent.HOME_LINEUP_MIDFIELD to item.strHomeLineupMidfield,
                        MatchEvent.HOME_LINEUP_FORWARD to item.strHomeLineupForward,
                        MatchEvent.HOME_LINEUP_SUBSTITUTES to item.strHomeLineupSubstitutes,

                        MatchEvent.AWAY_ID to item.idAwayTeam,
                        MatchEvent.AWAY_TEAM to item.strAwayTeam,
                        MatchEvent.AWAY_SCORE to item.intAwayScore,
                        MatchEvent.AWAY_GOAL_DETAILS to item.strAwayGoalDetails,
                        MatchEvent.AWAY_SHOTS to item.intAwayShots,
                        MatchEvent.AWAY_LINEUP_GOALKEEPER to item.strAwayLineupGoalkeeper,
                        MatchEvent.AWAY_LINEUP_DEFENSE to item.strAwayLineupDefense,
                        MatchEvent.AWAY_LINEUP_MIDFIELD to item.strAwayLineupMidfield,
                        MatchEvent.AWAY_LINEUP_FORWARD to item.strAwayLineupForward,
                        MatchEvent.AWAY_LINEUP_SUBSTITUTES to item.strAwayLineupSubstitutes
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
                delete(MatchEvent.FAVORITE_MATCH,
                        MatchEvent.ID_EVENT + "={id}", "id" to eventID)
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
