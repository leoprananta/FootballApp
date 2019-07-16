package com.example.leonanta.finalproject.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: DBOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBOpenHelper {
            if (instance == null) {
                instance = DBOpenHelper(ctx.applicationContext)
            }
            return instance as DBOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(MatchEvent.FAVORITE_MATCH, true,
                MatchEvent.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                MatchEvent.ID_EVENT to TEXT,
                MatchEvent.DATE to TEXT,
                MatchEvent.TIME to TEXT,

                MatchEvent.HOME_ID to TEXT,
                MatchEvent.HOME_TEAM to TEXT,
                MatchEvent.HOME_SCORE to TEXT,
                MatchEvent.HOME_GOAL_DETAILS to TEXT,
                MatchEvent.HOME_SHOTS to TEXT,
                MatchEvent.HOME_LINEUP_GOALKEEPER to TEXT,
                MatchEvent.HOME_LINEUP_DEFENSE to TEXT,
                MatchEvent.HOME_LINEUP_MIDFIELD to TEXT,
                MatchEvent.HOME_LINEUP_FORWARD to TEXT,
                MatchEvent.HOME_LINEUP_SUBSTITUTES to TEXT,

                MatchEvent.AWAY_ID to TEXT,
                MatchEvent.AWAY_TEAM to TEXT,
                MatchEvent.AWAY_SCORE to TEXT,
                MatchEvent.AWAY_GOAL_DETAILS to TEXT,
                MatchEvent.AWAY_SHOTS to TEXT,
                MatchEvent.AWAY_LINEUP_GOALKEEPER to TEXT,
                MatchEvent.AWAY_LINEUP_DEFENSE to TEXT,
                MatchEvent.AWAY_LINEUP_MIDFIELD to TEXT,
                MatchEvent.AWAY_LINEUP_FORWARD to TEXT,
                MatchEvent.AWAY_LINEUP_SUBSTITUTES to TEXT
        )

        db.createTable(AllTeam.FAVORITE_TEAM, true,
                AllTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                AllTeam.TEAM_ID to TEXT,
                AllTeam.TEAM to TEXT,
                AllTeam.FORMED_YEAR to TEXT,
                AllTeam.STADIUM to TEXT,
                AllTeam.TEAM_DESCRIPTION to TEXT,
                AllTeam.TEAM_BADGE to TEXT
        )

        db.createTable(AllPlayer.FAVORITE_PLAYER, true,
                AllPlayer.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                AllPlayer.PLAYER_ID to TEXT,
                AllPlayer.TEAM_ID to TEXT,
                AllPlayer.PLAYER to TEXT,
                AllPlayer.TEAM to TEXT,
                AllPlayer.PLAYER_DESCRIPTION to TEXT,
                AllPlayer.POSITION to TEXT,
                AllPlayer.WEIGHT to TEXT,
                AllPlayer.HEIGHT to TEXT,
                AllPlayer.CUTOUT to TEXT,
                AllPlayer.FANART to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(MatchEvent.FAVORITE_MATCH, true)
        db.dropTable(AllTeam.FAVORITE_TEAM, true)
        db.dropTable(AllPlayer.FAVORITE_PLAYER, true)
    }
}