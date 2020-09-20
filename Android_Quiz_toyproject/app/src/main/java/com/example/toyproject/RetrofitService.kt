package com.example.toyproject

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    // @Headers("content-type: application/json")
    @GET("quizzes/")
    fun getUserQuiz(): Call<QuizList>

    @GET("quizzes/{nickname}")
    fun getNicknameQuiz(
        @Path("nickname") nickname: String
    ): Call<QuizList>

    @POST("quizzes/{nickname}")
    fun postQuizScore(
        @Path("nickname") nickname: String,
        @Body params: HashMap<String, Any>
    ): Call<HashMap<String, String>>

}