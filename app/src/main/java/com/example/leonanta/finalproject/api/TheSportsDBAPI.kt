package com.example.leonanta.finalproject.api

import com.example.leonanta.finalproject.BuildConfig

object TheSportsDBAPI {

    fun getAllTeam(id: String): String {
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/lookup_all_teams.php?id=${id}"
    }

    fun getAllPlayer(id: String): String {
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/lookup_all_players.php?id=${id}"
    }

    fun getLastMatch(id: String): String {
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id=${id}"
    }

    fun getNextMatch(id: String): String {
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/eventsnextleague.php?id=${id}"
    }

    fun getTeamDetail(id: String): String {
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/lookupteam.php?id=${id}"
    }

    fun getTeamSearch(teamName: String): String {
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/searchteams.php?t=${teamName}"
    }

    fun getPlayerSearch(teamName: String, playerName: String): String {
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/searchplayers.php?t=${teamName}&p=${playerName}"
    }

    fun getEventSearch(eventName: String): String {
        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
                "/searchevents.php?e=${eventName}"
    }
}
