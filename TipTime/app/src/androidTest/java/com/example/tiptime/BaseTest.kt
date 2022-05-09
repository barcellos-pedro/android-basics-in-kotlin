package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*

open class BaseTest {
    fun calculate(
        costOfService: String,
        tipPercentage: Double,
        roundUpTip: Boolean = true
    ): Double {
        // Input cost of service value
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText(costOfService))
            .perform(closeSoftKeyboard())

        Thread.sleep(500)

        // Select tip percentage radio button
        val tipPercentageButton = when (tipPercentage) {
            0.20 -> R.id.option_twenty_percent
            0.18 -> R.id.option_eighteen_percent
            else -> R.id.option_fifteen_percent
        }

        onView(withId(tipPercentageButton))
            .perform(click())

        Thread.sleep(500)

        // Uncheck round up switch
        if (!roundUpTip) {
            onView(withId(R.id.round_up_switch))
                .perform(click())
        }

        Thread.sleep(500)

        // Click calculate button
        onView(withId(R.id.calculate_button))
            .perform(click())

        return tipFormula(costOfService, tipPercentage, roundUpTip)
    }

    /**
     * Perform tip calculation
     * */
    private fun tipFormula(
        costOfService: String,
        tipPercentage: Double,
        roundUpTip: Boolean
    ): Double {

        var tip = tipPercentage * costOfService.toDouble()

        return if (roundUpTip) {
            kotlin.math.ceil(tip)
        } else {
            tip
        }
    }

    /**
     * Check tip result on tip_result textView
     * */
    fun checkTipResult(result: Double) {
        // Ensure to get 2 decimals
        val tip = String.format("%.2f", result)

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$tip"))))
    }
}