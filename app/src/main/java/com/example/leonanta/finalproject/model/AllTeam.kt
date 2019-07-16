package com.example.leonanta.finalproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllTeam(

        val id: Long?,

        @SerializedName("idTeam")
        var idTeam: String? = "",

        @SerializedName("strTeam")
        var strTeam: String? = "",

        @SerializedName("intFormedYear")
        var intFormedYear: String? = "",

        @SerializedName("strStadium")
        var strStadium: String? = "",

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String = "",

        @SerializedName("strTeamBadge")
        var strTeamBadge: String? = ""

) : Parcelable {
    companion object {
        const val FAVORITE_TEAM: String = "FAVORITE_TEAM"
        const val ID: String = "ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM: String = "TEAM"
        const val FORMED_YEAR: String = "FORMED_YEAR"
        const val STADIUM: String = "STADIUM"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }
}