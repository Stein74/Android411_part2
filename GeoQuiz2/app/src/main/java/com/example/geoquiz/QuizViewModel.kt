package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true, false, false),
        Question(R.string.question_oceans, true, false, false),
        Question(R.string.question_mideast, false, false, false),
        Question(R.string.question_africa, false, false, false),
        Question(R.string.question_americas, true, false, false),
        Question(R.string.question_asia, true, false, false),
    )

    var currentIndex = 0
    var correctAnswers = 0
    var answers = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    var currentQuestionAnswered: Boolean
        get() = questionBank[currentIndex].answerChecked
        set(answer: Boolean) {
            questionBank[currentIndex].answerChecked = answer
        }
    var currentQuestionIsCheated: Boolean
        get() = questionBank[currentIndex].isCheater
        set(isCheater: Boolean) {
            questionBank[currentIndex].isCheater = isCheater
        }

    fun size(): Int {
        return questionBank.size
    }

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

}