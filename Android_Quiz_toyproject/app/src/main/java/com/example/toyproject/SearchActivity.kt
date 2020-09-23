package com.example.toyproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search_enter_btn.setOnClickListener {
            val nickname = search_editText.text.toString()  // 풀고 싶은 퀴즈 주인의 닉네임

            // if ()    // 입력한 닉네임이 존재하지 않을 경우

            // 입력한 닉네임이 존재할 경우
            Toast.makeText(this, "입력한 닉네임 존재", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SolveQuizActivity::class.java)
                .putExtra("nickname", nickname))    // 닉네임 전달
        }

        search_next_btn.setOnClickListener {
            search_btn.setImageResource(R.drawable.search_btn_no)
            search_next_btn.setImageResource(R.drawable.search_nextbtn_yes)
            startActivity(Intent(this, TestActivity::class.java))
        }

    }
}