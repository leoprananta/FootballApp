package com.example.leonanta.finalproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllPlayer(

        val id: Long?,

        @SerializedName("idPlayer")
        var idPlayer: String? = "",

        @SerializedName("idTeam")
        var idTeam: String? = "",

        @SerializedName("strPlayer")
        var strPlayer: String? = "",

        @SerializedName("strTeam")
        var strTeam: String? = "",

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = "",

        @SerializedName("strPosition")
        var strPosition: String? = "",

        @SerializedName("strHeight")
        var strHeight: String? = "",

        @SerializedName("strWeight")
        var strWeight: String? = "",

        @SerializedName("strCutout")
        var strCutout: String? = "",

        @SerializedName("strFanart1")
        var strFanart1: String? = ""

) : Parcelable {
    companion object {
        const val FAVORITE_PLAYER: String = "FAVORITE_PLAYER"
        const val ID: String = "ID"
        const val PLAYER_ID: String = "PLAYER_ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val PLAYER: String = "PLAYER"
        const val TEAM: String = "TEAM"
        const val PLAYER_DESCRIPTION: String = "PLAYER_DESCRIPTION"
        const val POSITION: String = "POSITION"
        const val HEIGHT: String = "HEIGHT"
        const val WEIGHT: String = "WEIGHT"
        const val CUTOUT: String = "CUTOUT"
        const val FANART: String = "FANART"
    }
}