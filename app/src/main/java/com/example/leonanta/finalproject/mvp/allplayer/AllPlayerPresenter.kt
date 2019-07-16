package com.example.leonanta.finalproject.mvp.allplayer

import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.api.TheSportsDBAPI
import com.example.leonanta.finalproject.model.AllPlayerResponse
import com.example.leonanta.finalproject.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class AllPlayerPresenter(private val view: AllPlayerView,
                         private val APIRepository: APIRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getAllPlayer(id: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(APIRepository
                        .doRequest(TheSportsDBAPI.getAllPlayer(id)),
                        AllPlayerResponse::class.java
                )
            }
            try {
                view.showAllPlayer(data.await().player!!)
                view.hideLoading()
            } catch (e: NullPointerException) {
                view.showNoData()
            }
        }
    }

    fun getPlayerSearch(teamName: String, playerName: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(APIRepository
                        .doRequest(TheSportsDBAPI.getPlayerSearch(teamName, playerName)),
                        AllPlayerResponse::class.java
                )
            }
            try {
                view.showAllPlayer(data.await().player!!)
                view.hideLoading()
            } catch (e: NullPointerException) {
                view.showNoData()
            }
        }
    }
}