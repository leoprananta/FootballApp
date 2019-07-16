package com.example.leonanta.finalproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamDetail(

        @SerializedName("idTeam")
        var idTeam: String? = "",

        @SerializedName("strTeamBadge")
        var strTeamBadge: String? = ""

) : Parcelable