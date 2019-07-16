package com.example.leonanta.finalproject.mvp.allplayer

import com.example.leonanta.finalproject.model.AllPlayer

interface AllPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showNoData()
    fun showAllPlayer(data: List<AllPlayer>)
}