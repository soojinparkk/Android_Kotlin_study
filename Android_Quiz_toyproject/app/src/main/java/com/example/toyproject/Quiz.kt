package com.example.toyproject

import java.io.Serializable

data class QuizList (
    var nickname: String = "",
    var quizList: ArrayList<Quiz>
): Serializable

data class Quiz (
    val id: Int = 0,
    val content: String = "",
    var answer: Int = 0,
): Serializable