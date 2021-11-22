package com.example.topquizzandroid.controler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.topquizzandroid.R
import com.example.topquizzandroid.model.QuestionBank

import com.example.topquizzandroid.model.Question
import java.util.*


class GameActivity : AppCompatActivity() {
    private lateinit var mQuestionTextView: TextView
    private lateinit var mAnswer1Button: Button
    private lateinit var mAnswer2Button: Button
    private lateinit var mAnswer3Button: Button
    private lateinit var mAnswer4Button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        mQuestionTextView = findViewById(R.id.game_activity_textview_question)
        mAnswer1Button = findViewById(R.id.game_activity_button_1)
        mAnswer2Button = findViewById(R.id.game_activity_button_2)
        mAnswer3Button = findViewById(R.id.game_activity_button_3)
        mAnswer4Button = findViewById(R.id.game_activity_button_4)
    }

    private fun generateQuestionBank(): QuestionBank? {
        val question1 = Question(
            "Who is the creator of Android?",
            Arrays.asList(
                "Andy Rubin",
                "Steve Wozniak",
                "Jake Wharton",
                "Paul Smith"
            ),
            0
        )
        val question2 = Question(
            "When did the first man land on the moon?",
            Arrays.asList(
                "1958",
                "1962",
                "1967",
                "1969"
            ),
            3
        )
        val question3 = Question(
            "What is the house number of The Simpsons?",
            Arrays.asList(
                "42",
                "101",
                "666",
                "742"
            ),
            3
        )
        val question4 = Question(
            "Who did the Mona Lisa paint?",
            Arrays.asList(
                "Michelangelo",
                "Leonardo Da Vinci",
                "Raphael",
                "Carravagio"
            ),
            1
        )
        val question5 = Question(
            "What is the country top-level domain of Belgium?",
            Arrays.asList(
                ".bg",
                ".bm",
                ".bl",
                ".be"
            ),
            3
        )
        return QuestionBank(Arrays.asList(question1, question2, question3, question4, question5))
    }
}