package com.example.leonanta.finalproject.mvp.next

import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.api.TheSportsDBAPI
import com.example.leonanta.finalproject.model.MatchEventResponse
import com.example.leonanta.finalproject.model.SearchEventResponse
import com.example.leonanta.finalproject.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextPresenter(private val view: NextView,
                    private val APIRepository: APIRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getNextMatch(id: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(APIRepository
                        .doRequest(TheSportsDBAPI.getNextMatch(id)),
                        MatchEventResponse::class.java
                )
            }
            try {
                view.showNextMatch(data.await().events!!)
                view.hideLoading()
            } catch (e: NullPointerException) {
                view.showNoData()
            }
        }
    }

    fun getEventSearch(eventName: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(APIRepository
                        .doRequest(TheSportsDBAPI.getEventSearch(eventName)),
                        SearchEventResponse::class.java
                )
            }
            try {
                view.showLastMatch(data.await().events!!)
                view.hideLoading()
            } catch (e: NullPointerException) {
                view.showNoData()
            }
        }
    }
}
