package com.example.leonanta.finalproject.utils

import org.junit.Test

import org.junit.Assert.*

class DateTest {

    @Test
    fun getDate() {
        assertEquals("Sab, 10 Nov 2018", Date.getDate("2018-11-10"))
    }
}