package com.example.leonanta.finalproject.mvp

import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.api.TheSportsDBAPI
import com.example.leonanta.finalproject.model.MatchEvent
import com.example.leonanta.finalproject.model.MatchEventResponse
import com.example.leonanta.finalproject.mvp.last.LastPresenter
import com.example.leonanta.finalproject.mvp.last.LastView
import com.example.leonanta.finalproject.utils.ContextProviderTest
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetLastMatchTest {

    @Mock
    private
    lateinit var view: LastView

    @Mock
    private
    lateinit var apiRepository: APIRepository

    @Mock
    private
    lateinit var gson: Gson
    private lateinit var presenter: LastPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastPresenter(view, apiRepository, gson, ContextProviderTest())
    }

    @Test
    fun getLastMatchTest() {
        val matchEvent: MutableList<MatchEvent> = mutableListOf()
        val response = MatchEventResponse(matchEvent)
        val idMatch = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportsDBAPI.getLastMatch(idMatch)),
                MatchEventResponse::class.java)
        ).thenReturn(response)

        presenter.getLastMatch(idMatch)

        verify(view).showLoading()
        verify(view).showLastMatch(response.events!!)
        verify(view).hideLoading()
    }
}