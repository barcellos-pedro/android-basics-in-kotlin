package com.example.debugging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logging()
        division()
    }

    fun logging() {
        Log.v(TAG, "Hello, world!")
    }

    fun division() {
        var numerator = 60
        var denominator = 4

        repeat(4) {
            var result = numerator / denominator
            Log.d(TAG, "Denominator: $denominator")
            Log.v(TAG, "$numerator / $denominator = $result")
            denominator--
        }
    }
}