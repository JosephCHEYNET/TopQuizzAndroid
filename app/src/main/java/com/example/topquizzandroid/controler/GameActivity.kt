package com.example.topquizzandroid.controler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.topquizzandroid.R
import com.example.topquizzandroid.model.Question
import com.example.topquizzandroid.model.QuestionBank
import java.lang.IllegalStateException
import android.view.MotionEvent
import com.google.gson.Gson


const val BUNDLE_SCORE = "BUNDLE_SCORE"
const val BUNDLE_QUESTION_BANK = "BUNDLE_EXTRA_SCORE"
const val REMAINING_QUESTION_COUNT = "REMAINING_QUESTION_COUNT"
val gson = Gson()

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var questionTextView: TextView
    private lateinit var answer1Button: Button
    private lateinit var answer2Button: Button
    private lateinit var answer3Button: Button
    private lateinit var answer4Button: Button
    private var remainingQuestionCount = 4
    private var score = 0
    private var questionBank = generateQuestionBank()
    private var mEnableTouchEvents = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            questionBank = gson.fromJson(
                savedInstanceState.getString(BUNDLE_QUESTION_BANK),
                questionBank::class.java
            )
            score = savedInstanceState.getInt(BUNDLE_SCORE)
            remainingQuestionCount = savedInstanceState.getInt(REMAINING_QUESTION_COUNT)
        } else {
            score = 0;
            remainingQuestionCount = 4;
        }

        mEnableTouchEvents = true
        setContentView(R.layout.activity_game)
        questionTextView = findViewById(R.id.game_activity_textview_question)
        answer1Button = findViewById(R.id.game_activity_button_1)
        answer2Button = findViewById(R.id.game_activity_button_2)
        answer3Button = findViewById(R.id.game_activity_button_3)
        answer4Button = findViewById(R.id.game_activity_button_4)
        answer1Button.setOnClickListener(this)
        answer2Button.setOnClickListener(this)
        answer3Button.setOnClickListener(this)
        answer4Button.setOnClickListener(this)
        displayQuestion(questionBank.getCurrentQuestion())
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev)
    }

    private fun displayQuestion(question: Question) {
        questionTextView.text = question.question
        answer1Button.text = question.choiceList[0]
        answer2Button.text = question.choiceList[1]
        answer3Button.text = question.choiceList[2]
        answer4Button.text = question.choiceList[3]
    }

    private fun generateQuestionBank(): QuestionBank {
        val question1 = Question(
            "Who is the creator of Android?",
            listOf(
                "Andy Rubin",
                "Steve Wozniak",
                "Jake Wharton",
                "Paul Smith"
            ),
            0
        )
        val question2 = Question(
            "When did the first man land on the moon?",
            listOf(
                "1958",
                "1962",
                "1967",
                "1969"
            ),
            3
        )
        val question3 = Question(
            "What is the house number of The Simpsons?",
            listOf(
                "42",
                "101",
                "666",
                "742"
            ),
            3
        )
        val question4 = Question(
            "Who did the Mona Lisa paint?",
            listOf(
                "Michelangelo",
                "Leonardo Da Vinci",
                "Raphael",
                "Carravagio"
            ),
            1
        )
        val question5 = Question(
            "What is the country top-level domain of Belgium?",
            listOf(
                ".bg",
                ".bm",
                ".bl",
                ".be"
            ),
            3
        )
        return QuestionBank(listOf(question1, question2, question3, question4, question5))
    }

    override fun onClick(v: View?) {
        val index = when {
            v === answer1Button -> {
                0
            }
            v === answer2Button -> {
                1
            }
            v === answer3Button -> {
                2
            }
            v === answer4Button -> {
                3
            }
            else -> {
                throw IllegalStateException("Unknown clicked view : $v")
            }
        }
        if (index == questionBank.getCurrentQuestion().answerIndex) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Lost!", Toast.LENGTH_SHORT).show()
        }
        mEnableTouchEvents = false
        Handler().postDelayed(Runnable {
            remainingQuestionCount--
            mEnableTouchEvents = true
            if (remainingQuestionCount > 0) {
                displayQuestion(questionBank.getNextQuestion())
            } else {
                val intent = Intent()
                intent.putExtra(BUNDLE_EXTRA_SCORE, score)
                getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putInt(SHARED_PREF_USER_INFO_SCORE, score)
                    .apply()
                setResult(RESULT_OK, intent)
                finish()
            }
        }, 2000) // LENGTH_SHORT is usually 2 second long
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(BUNDLE_QUESTION_BANK, gson.toJson(questionBank))
        outState.putInt(BUNDLE_SCORE, score)
        outState.putInt(REMAINING_QUESTION_COUNT, remainingQuestionCount)
    }
}