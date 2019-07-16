package com.example.leonanta.finalproject.mvp.last

import com.example.leonanta.finalproject.model.MatchEvent

interface LastView {

    fun showLoading()
    fun hideLoading()
    fun showNoData()
    fun showLastMatch(data: List<MatchEvent>)
    fun showNextMatch(data: List<MatchEvent>)
}
