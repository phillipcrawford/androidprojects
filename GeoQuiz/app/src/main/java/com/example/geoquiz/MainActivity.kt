package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        Log.d(TAG,"Phillip was here")
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")
        binding.questionTextView.setOnClickListener { view: View ->
            incrementIndex()
            updateQuestion()
            resetButtons()
        }
        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            disableButtons()
        }
        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            disableButtons()
        }
        binding.previousButton.setOnClickListener {
            decrementIndex()
            quizViewModel.clickedPreviousFirst()
            updateQuestion()
            resetButtons()
        }
        binding.nextButton.setOnClickListener {
            if (quizViewModel.reachEnd()){
                score()
                quizViewModel.resetScore()
            }
            incrementIndex()
            updateQuestion()
            resetButtons()
        }
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion(){
        //val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        //val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == correctAnswer) {
            quizViewModel.incrementCorrectAnswers()
            R.string.correct_toast
        } else {
            quizViewModel.incrementIncorrectAnswers()
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun resetButtons(){
        binding.trueButton.isEnabled = true
        binding.trueButton.isClickable = true
        binding.falseButton.isEnabled = true
        binding.falseButton.isClickable = true
    }

    private fun disableButtons(){
        binding.trueButton.isEnabled = false
        binding.trueButton.isClickable = false
        binding.falseButton.isEnabled = false
        binding.falseButton.isClickable = false
    }

    private fun incrementIndex(){
        //currentIndex = (currentIndex + 1) % questionBank.size
        quizViewModel.moveToNext()
    }

    private fun decrementIndex(){
        //currentIndex = (currentIndex - 1) % questionBank.size
        quizViewModel.moveToPrevious()
    }

    private fun score(){
        val score = quizViewModel.score()
        Toast.makeText(this, "Your score is $score%", Toast.LENGTH_SHORT).show()
    }

}