package com.example.leonanta.finalproject.api

import java.net.URL

class APIRepository {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}
