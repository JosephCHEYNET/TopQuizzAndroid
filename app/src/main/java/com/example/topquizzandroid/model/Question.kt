package com.example.topquizzandroid.model

import androidx.annotation.NonNull




class Question (question: String, choiceList: List<String>, answerIndex: Int){
    var mQuestion: String = question
    var mChoiceList: List<String> = choiceList
    var mAnswerIndex:Int = 0

    fun getQuestion(): String {
        return mQuestion
    }

    fun getChoiceList(): List<String?> {
        return mChoiceList
    }

    fun getAnswerIndex(): Int {
        return mAnswerIndex
    }

}