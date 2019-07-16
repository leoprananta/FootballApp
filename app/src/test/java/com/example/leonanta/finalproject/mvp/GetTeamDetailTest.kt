package com.example.leonanta.finalproject.mvp

import com.example.leonanta.finalproject.api.APIRepository
import com.example.leonanta.finalproject.api.TheSportsDBAPI
import com.example.leonanta.finalproject.model.TeamDetail
import com.example.leonanta.finalproject.model.TeamDetailResponse
import com.example.leonanta.finalproject.mvp.second.SecondPresenter
import com.example.leonanta.finalproject.mvp.second.SecondView
import com.example.leonanta.finalproject.utils.ContextProviderTest
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetTeamDetailTest {

    @Mock
    private
    lateinit var view: SecondView

    @Mock
    private
    lateinit var apiRepository: APIRepository

    @Mock
    private
    lateinit var gson: Gson
    private lateinit var presenter: SecondPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SecondPresenter(view, apiRepository, gson, ContextProviderTest())
    }

    @Test
    fun getTeamDetail() {
        val teamDetail: MutableList<TeamDetail> = mutableListOf()
        val response = TeamDetailResponse(teamDetail)
        val idTeam = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportsDBAPI.getTeamDetail(idTeam)),
                TeamDetailResponse::class.java)
        ).thenReturn(response)

        presenter.getTeamDetail(idTeam, idTeam)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamDetail(response.teams!!, response.teams!!)
        Mockito.verify(view).hideLoading()
    }
}