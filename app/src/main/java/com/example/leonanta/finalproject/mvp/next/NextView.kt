package com.example.leonanta.finalproject.mvp.next

import com.example.leonanta.finalproject.model.MatchEvent

interface NextView {

    fun showLoading()
    fun hideLoading()
    fun showNoData()
    fun showLastMatch(data: List<MatchEvent>)
    fun showNextMatch(data: List<MatchEvent>)
}