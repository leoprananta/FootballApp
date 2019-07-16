package com.example.leonanta.finalproject.model

val android.content.Context.DB: DBOpenHelper
    get() = DBOpenHelper.getInstance(applicationContext)