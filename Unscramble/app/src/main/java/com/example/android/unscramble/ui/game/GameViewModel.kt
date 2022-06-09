package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var _score = 0

    // Backing Property
    val score: Int
        get() = _score

    private var _currentWordCount = 0
    val currentWordCount: Int
        get() = _currentWordCount

    private lateinit var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord

    // list to hold words that haven been used
    private var wordsList: MutableList<String> = mutableListOf()

    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    /**
     * Returns true if the current word count is less than MAX_NO_OF_WORDS.
     * Updates the next word.
     * */
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        return if (playerWord.equals(currentWord, ignoreCase = true)) {
            increaseScore()
            return true
        } else false
    }

    /**
     * Re-initializes the game data to restart the game
     * */
    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
        getNextWord()
    }

    /**
     * Updates currentWord and currentScrambledWord with the next word.
     * */
    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()

        // ensure the scrambled word is not equal to the currentWord
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        // if the current word is in the wordsList
        // then this word has already been taken
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            _currentWordCount++
            wordsList.add(currentWord)
        }
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }
}