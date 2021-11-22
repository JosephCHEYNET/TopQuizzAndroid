package com.example.topquizzandroid.model

import java.util.Collections;

class QuestionBank(questionList: List<Question>) {
    var mQuestionList: List<Question> = questionList
    var mQuestionIndex: Int = 0
    init {
        Collections.shuffle(mQuestionList);
    }
    // Loop over the questions and return a new one at each call
    fun getNextQuestion(): Question? {
        mQuestionIndex++
        return getCurrentQuestion()
    }

    fun getCurrentQuestion(): Question? {
        return mQuestionList[mQuestionIndex]
    }
}