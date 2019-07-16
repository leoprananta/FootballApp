package com.example.leonanta.finalproject.mvp.team

import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.api.TheSportsDBAPI
import com.example.leonanta.finalproject.model.AllTeamResponse
import com.example.leonanta.finalproject.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(private val view: TeamView,
                    private val APIRepository: APIRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getAllTeam(id: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(APIRepository
                        .doRequest(TheSportsDBAPI.getAllTeam(id)),
                        AllTeamResponse::class.java
                )
            }
            try {
                view.showAllTeam(data.await().teams)
                view.hideLoading()
            } catch (e: NullPointerException) {
                view.showNoData()
            }
        }
    }

    fun getTeamSearch(teamName: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(APIRepository
                        .doRequest(TheSportsDBAPI.getTeamSearch(teamName)),
                        AllTeamResponse::class.java
                )
            }
            try {
                view.showAllTeam(data.await().teams)
                view.hideLoading()
            } catch (e: NullPointerException) {
                view.showNoData()
            }
        }
    }
}