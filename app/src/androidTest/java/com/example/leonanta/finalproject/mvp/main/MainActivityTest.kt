package com.example.leonanta.finalproject.mvp.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.leonanta.finalproject.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    var activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testLast() {
        onView(withId(bnav_last)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(bnav_last)).perform(click())
        Thread.sleep(500)
        onView(withId(last_spinner)).check(matches(isDisplayed()))
        onView(withId(last_recyclerView)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(last_spinner)).perform(ViewActions.click())
        Thread.sleep(500)
        onView(withText("German Bundesliga")).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(withId(last_recyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(14))
        Thread.sleep(1000)
        onView(withId(last_recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(12, click()))
        Thread.sleep(1000)
        onView(withId(addToFav)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(addToFav)).perform(click())
        Thread.sleep(1000)
        pressBack()
    }

    @Test
    fun testNext(){
        onView(withId(bnav_next)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(bnav_next)).perform(click())
        Thread.sleep(500)
        onView(withId(next_spinner)).check(matches(isDisplayed()))
        onView(withId(next_recyclerView)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(next_spinner)).perform(ViewActions.click())
        Thread.sleep(500)
        onView(withText("Spanish La Liga")).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(withId(next_recyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(1000)
        onView(withId(next_recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))
        Thread.sleep(1000)
        onView(withId(addToFav)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(addToFav)).perform(click())
        Thread.sleep(1000)
        pressBack()
    }

    @Test
    fun testFavorite(){
        onView(withId(bnav_fav)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(bnav_fav)).perform(click())
        Thread.sleep(500)
        onView(withId(fav_match_recyclerView)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(fav_match_recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)
        onView(withId(addToFav)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(addToFav)).perform(click())
        Thread.sleep(1000)
        pressBack()
    }

}