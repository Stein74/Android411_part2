package com.example.geoquiz

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var cheatButton: Button
    private lateinit var nextButton: ImageView
    private lateinit var prevButton: ImageView
    private lateinit var questionTextView: TextView
    private lateinit var resultTextView: TextView

    val cheatActivityLauncher = registerForActivityResult(CheatActivityContract()) {
                result -> quizViewModel.currentQuestionIsCheated = result
    }

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    private val RESULT = "Correct answers: %d%%"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  currentIndex = savedInstanceState?.getInt(KEY_INDEX)?:0
        quizViewModel.currentIndex = currentIndex

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        cheatButton = findViewById(R.id.cheat_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)
        resultTextView = findViewById(R.id.result_text_view)

        trueButton.setOnClickListener(){ view: View ->
            checkAnswer(true)
        }
        falseButton.setOnClickListener(){ view: View ->
            checkAnswer(false)
        }
        cheatButton.setOnClickListener(){ view: View ->
            cheatActivityLauncher.launch(quizViewModel.currentQuestionAnswer)
        }
        nextButton.setOnClickListener(){view: View ->
            quizViewModel.moveToNext()
            updateQuestion()
        }
        prevButton.setOnClickListener(){view: View ->
            quizViewModel.moveToPrev()
            updateQuestion()
        }

        questionTextView.setOnClickListener(){view: View ->
            quizViewModel.moveToNext()
            updateQuestion()
        }
        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        savedStateRegistry
    }

    private fun updateQuestion(){
        questionTextView.setText(quizViewModel.currentQuestionText)
        blockButtons(quizViewModel.currentIndex)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        quizViewModel.answers++

/*        var messageResId: Int
        if (userAnswer == correctAnswer){
            messageResId = R.string.correct_toast
            quizViewModel.correctAnswers++
        } else {
            messageResId = R.string.incorrect_toast
        }*/

        val messageResId = when{
            quizViewModel.currentQuestionIsCheated -> R.string.judgment_toast
            userAnswer == correctAnswer -> {
                quizViewModel.correctAnswers++
                R.string.correct_toast
            }
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        quizViewModel.currentQuestionAnswered = true
        blockButtons(quizViewModel.currentIndex)

        if (quizViewModel.answers == quizViewModel.size()){
            val persent = Math.round(quizViewModel.correctAnswers.toDouble() /
                    quizViewModel.answers * 100)
            resultTextView.setText(String.format(RESULT, persent))
        }
    }

    private fun blockButtons(currentIndex: Int) {
        trueButton.isEnabled = !quizViewModel.currentQuestionAnswered
        falseButton.isEnabled = !quizViewModel.currentQuestionAnswered
    }
}