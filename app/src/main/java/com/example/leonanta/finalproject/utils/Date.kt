package com.example.leonanta.finalproject.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Date {

    private fun formatDate(date: String, format: String, isDate: Boolean): String {
        var finalresult = ""
        val localId = Locale("in")
        val old = SimpleDateFormat(if (isDate) "yyyy-MM-dd" else "HH:mm:ss", localId)
        try {
            val oldDate = old.parse(date)
            val newDate = SimpleDateFormat(format, localId)
            finalresult = newDate.format(oldDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return finalresult
    }

    fun getDate(date: String?): String {
        return formatDate(date.toString(), "EEE, dd MMM yyyy", true)
    }

    fun getTime(date: String?): String {
        return formatDate(date.toString(), "HH:mm", false)
    }
}
