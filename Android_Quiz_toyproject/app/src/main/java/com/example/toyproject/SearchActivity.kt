package com.example.toyproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val userNickname = intent.getStringExtra("userNickname").toString()

        search_enter_btn.setOnClickListener {
            val nickname = search_editText.text.toString()  // 퀴즈 주인 닉네임

            // 닉네임을 입력하지 않았을 경우
            if (nickname == "") {
                Toast.makeText(this, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                // 입력한 닉네임 존재 여부 확인
                val body = HashMap<String, String>()
                body.put("nickname", nickname)

                (application as MasterApplication).service.getNicknameIsExist(body)
                    .enqueue(object : Callback<HashMap<String, String>>{
                        override fun onFailure(call: Call<HashMap<String, String>>, t: Throwable) {
                            Toast.makeText(this@SearchActivity, "닉네임 조회 실패", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(
                            call: Call<HashMap<String, String>>,
                            response: Response<HashMap<String, String>>
                        ) {
                            if (response.isSuccessful) {
                                val result = response.body()

                                // 입력한 닉네임이 존재하지 않는 경우
                                if (result!!.get("success") == "true") {
                                    Toast.makeText(this@SearchActivity, "존재하지 않는 닉네임입니다", Toast.LENGTH_SHORT).show()
                                } else {
                                    // 입력한 닉네임이 존재하는 경우
                                    // BUT 닉네임은 존재하지만 해당 닉네임 유저가 아직 퀴즈를 생성하지 않았을 경우
                                    // 퀴즈 생성 여부를 알 수 있는 방법을 추가해야 함
                                    startActivity(Intent(this@SearchActivity, SolveQuizActivity::class.java)
                                        .putExtra("nickname", nickname)             // 퀴즈 주인 닉네임 전달
                                        .putExtra("userNickname", userNickname))    // 현재 로그인 상태인 유저 닉네임 전달
                                }
                            }
                        }
                    })

            }
        }

        // 내 정보 액티비티 클릭할 경우
        // 1) 생성한 퀴즈가 없을 경우: CreateQuizActivity로 전환
        // 2) 생성한 퀴즈가 있을 경우: RankActivity로 전환
        search_next_btn.setOnClickListener {
            search_btn.setImageResource(R.drawable.search_btn_no)
            search_next_btn.setImageResource(R.drawable.search_next_yes)
/*
            if () {

                startActivity(Intent(this, CreateQuizActivity::class.java)
                    .putExtra("userNickname", userNickname))    // 현재 로그인 상태인 유저 닉네임 전달
            } else {
                Log.d("soo", "check"+check)
                startActivity(Intent(this, RankActivity::class.java)
                    .putExtra("userNickname", userNickname)
                    .putExtra("nickname", userNickname))
            }
*/
        }

    }
}