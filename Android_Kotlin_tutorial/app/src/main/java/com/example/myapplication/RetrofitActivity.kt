package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        // baseUrl() -> 서버 도메인 주소 부분 (변하지 않는 부분)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        // GET 요청
        service.getStudentsList().enqueue(object : Callback<ArrayList<PersonFromServer>> {
            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) {
                // 보통 error massage 넣음
            }

            override fun onResponse(
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                if (response.isSuccessful) {
                    val personList = response.body()
                    val statusCode = response.code()
                    val error = response.errorBody()
                    val header = response.headers()
                }
            }
        })

        // POST 요청 (1)
//        val params = HashMap<String, Any>()
//        params.put("name", "김")
//        params.put("age", 20)
//        params.put("intro", "안녕하세요")
//
//        service.createStudent(params).enqueue(object : Callback<PersonFromServer> {
//            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
//            }
//
//            override fun onResponse(
//                call: Call<PersonFromServer>,
//                response: Response<PersonFromServer>
//            ) {
//                if (response.isSuccessful) {
//                    val person = response.body()
//                    Log.d("retrofitt", "name: "+person?.name)
//                }
//            }
//        })

        // POST 요청 (2)
        val person = PersonFromServer(name = "철수", age = 23, intro = "안녕하십니까")
        service.createStudentEasy(person).enqueue(object : Callback<PersonFromServer> {
            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if (response.isSuccessful) {
                    val person = response.body()
                    Log.d("retrofitt", "name: "+person?.name)
                    Log.d("retrofitt", "age: "+person?.age)
                    Log.d("retrofitt", "intro: "+person?.intro)
                }
            }
        })

    }
}
