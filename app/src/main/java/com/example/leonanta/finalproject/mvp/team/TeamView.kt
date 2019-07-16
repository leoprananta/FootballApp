package com.example.leonanta.finalproject.mvp.team

import com.example.leonanta.finalproject.model.AllTeam

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showNoData()
    fun showAllTeam(data: List<AllTeam>)
}