package com.example.leonanta.finalproject.api

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class APIRepositoryTest {
    @Test
    fun testDoRequest(){
        val APIRepository = mock(APIRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        APIRepository.doRequest(url)
        verify(APIRepository).doRequest(url)
    }
}