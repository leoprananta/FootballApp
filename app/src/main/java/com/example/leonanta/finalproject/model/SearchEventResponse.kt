package com.example.leonanta.finalproject.model

import com.google.gson.annotations.SerializedName

data class SearchEventResponse(

        @field:SerializedName("event")
        val events: MutableList<MatchEvent>?
)