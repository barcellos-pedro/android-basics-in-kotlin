package com.example.tiptime

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.containsString
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CalculatorTests : BaseTest() {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tiptime", appContext.packageName)
    }

    @Test
    fun calculate_20_percent_tip_roundup_true() {
        calculate("50.00", 0.20).also {
            checkTipResult(it)
        }
    }

    @Test
    fun calculate_18_percent_tip_roundup_false() {
        var result = calculate("28.90", 0.18, false)
        checkTipResult(result)
    }

    @Test
    fun calculate_15_percent_tip_roundup_true() {
        var result: Double = calculate("23.75", 0.15)
        checkTipResult(result)
    }
}