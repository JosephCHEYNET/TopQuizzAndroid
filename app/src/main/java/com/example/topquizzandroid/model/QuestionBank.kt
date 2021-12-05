package com.example.topquizzandroid.model

import java.util.Collections.shuffle

class QuestionBank(private var questionList: List<Question>) {
    private var questionIndex: Int = 0
    init {
        shuffle(questionList)
    }

    // Loop over the questions and return a new one at each call
    fun getNextQuestion(): Question {
        questionIndex++
        return getCurrentQuestion()
    }

    fun getCurrentQuestion(): Question {
        return questionList[questionIndex]
    }
}