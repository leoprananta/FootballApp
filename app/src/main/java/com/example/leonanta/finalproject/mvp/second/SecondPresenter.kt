package com.example.leonanta.finalproject.mvp.second

import com.google.gson.Gson
import com.example.leonanta.finalproject.model.TeamDetailResponse
import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.api.TheSportsDBAPI
import com.example.leonanta.finalproject.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SecondPresenter(private val view: SecondView,
                      private val APIRepository: APIRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(homeTeam: String, awayTeam: String) {
        view.showLoading()
        async(context.main) {
            val dataHomeTeam = bg {
                gson.fromJson(APIRepository
                        .doRequest(TheSportsDBAPI.getTeamDetail(homeTeam)),
                        TeamDetailResponse::class.java
                )
            }
            val dataAwayTeam = bg {
                gson.fromJson(APIRepository
                        .doRequest(TheSportsDBAPI.getTeamDetail(awayTeam)),
                        TeamDetailResponse::class.java
                )
            }
            try {
                view.hideLoading()
                view.showTeamDetail(dataHomeTeam.await().teams!!, dataAwayTeam.await().teams!!)
            } catch (e: NullPointerException) {
                view.showNoData()
            }
        }
    }
}
