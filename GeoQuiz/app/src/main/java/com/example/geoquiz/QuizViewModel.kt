package com.example.geoquiz

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    init {
        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private var correctAnswers = 0
    private var incorrectAnswers = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious(){
        currentIndex = (currentIndex - 1) % questionBank.size
    }

    fun clickedPreviousFirst(){
        if (currentIndex == -1) {
            currentIndex = questionBank.size - 1
        }
    }

    fun incrementCorrectAnswers(){
        correctAnswers = correctAnswers + 1
    }

    fun incrementIncorrectAnswers(){
        incorrectAnswers = incorrectAnswers + 1
    }

    fun reachEnd(): Boolean{
        return currentIndex == questionBank.size - 1
    }

    fun score(){
        val score = correctAnswers.toDouble() / questionBank.size * 100
    }
}