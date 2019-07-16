package com.example.leonanta.finalproject.mvp.second

import com.example.leonanta.finalproject.model.TeamDetail

interface SecondView {

    fun showLoading()
    fun hideLoading()
    fun showNoData()
    fun showTeamDetail(dataHome: List<TeamDetail>, dataAway: List<TeamDetail>)
}
