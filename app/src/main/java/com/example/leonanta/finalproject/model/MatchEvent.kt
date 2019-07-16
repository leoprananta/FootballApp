package com.example.leonanta.finalproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchEvent(

        val id: Long?,

        @SerializedName("idEvent")
        var idEvent: String? = "",

        @SerializedName("dateEvent")
        var dateEvent: String? = "",

        @SerializedName("strTime")
        var strTime: String? = "",


        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = "",

        @SerializedName("strHomeTeam")
        var strHomeTeam: String? = "",

        @SerializedName("intHomeScore")
        var intHomeScore: String? = "",

        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String? = "",

        @SerializedName("intHomeShots")
        var intHomeShots: String? = "",

        @SerializedName("strHomeLineupGoalkeeper")
        var strHomeLineupGoalkeeper: String? = "",

        @SerializedName("strHomeLineupDefense")
        var strHomeLineupDefense: String? = "",

        @SerializedName("strHomeLineupMidfield")
        var strHomeLineupMidfield: String? = "",

        @SerializedName("strHomeLineupForward")
        var strHomeLineupForward: String? = "",

        @SerializedName("strHomeLineupSubstitutes")
        var strHomeLineupSubstitutes: String? = "",


        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = "",

        @SerializedName("strAwayTeam")
        var strAwayTeam: String? = "",

        @SerializedName("intAwayScore")
        var intAwayScore: String? = "",

        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String? = "",

        @SerializedName("intAwayShots")
        var intAwayShots: String? = "",

        @SerializedName("strAwayLineupGoalkeeper")
        var strAwayLineupGoalkeeper: String? = "",

        @SerializedName("strAwayLineupDefense")
        var strAwayLineupDefense: String? = "",

        @SerializedName("strAwayLineupMidfield")
        var strAwayLineupMidfield: String? = "",

        @SerializedName("strAwayLineupForward")
        var strAwayLineupForward: String? = "",

        @SerializedName("strAwayLineupSubstitutes")
        var strAwayLineupSubstitutes: String? = ""

) : Parcelable {
    companion object {
        const val FAVORITE_MATCH: String = "FAVORITE_MATCH"
        const val ID: String = "ID"
        const val ID_EVENT: String = "ID_EVENT"
        const val DATE: String = "DATE"
        const val TIME: String = "TIME"

        const val HOME_ID: String = "HOME_ID"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val HOME_GOAL_DETAILS: String = "HOME_GOAL_DETAILS"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val HOME_LINEUP_GOALKEEPER: String = "HOME_LINEUP_GOALKEEPER"
        const val HOME_LINEUP_DEFENSE: String = "HOME_LINEUP_DEFENSE"
        const val HOME_LINEUP_MIDFIELD: String = "HOME_LINEUP_MIDFIELD"
        const val HOME_LINEUP_FORWARD: String = "HOME_LINEUP_FORWARD"
        const val HOME_LINEUP_SUBSTITUTES: String = "HOME_LINEUP_SUBSTITUTES"

        const val AWAY_ID: String = "AWAY_ID"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val AWAY_GOAL_DETAILS: String = "AWAY_GOAL_DETAILS"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val AWAY_LINEUP_GOALKEEPER: String = "AWAY_LINEUP_GOALKEEPER"
        const val AWAY_LINEUP_DEFENSE: String = "AWAY_LINEUP_DEFENSE"
        const val AWAY_LINEUP_MIDFIELD: String = "AWAY_LINEUP_MIDFIELD"
        const val AWAY_LINEUP_FORWARD: String = "AWAY_LINEUP_FORWARD"
        const val AWAY_LINEUP_SUBSTITUTES: String = "AWAY_LINEUP_SUBSTITUTES"
    }
}
