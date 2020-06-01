package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_listener.*

class Listener : AppCompatActivity() {

    var num = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listener)

        // view를 activity로 가져오는 방법
        // 1. 직접 찾아서 가져옴
        //val textView : TextView = findViewById(R.id.hello)
        // 2. xml File을 import해서 가져옴
        // hello.

        // 1. 람다 방식
        hello.setOnClickListener {
            Log.d("click", "Click!!")
        }

        // 2. 익명함수 방식
        hello.setOnClickListener (object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("click", "Click!!")
            }
        })

        // 3. 이름이 필요한 경우(click)
        val click = object : View.OnClickListener {
            override fun onClick(v: View?) {
                hello.setText("안녕하세요")
                image.setImageResource(R.drawable.solid)

                num += 10
                Log.d("number", ""+num)
            }
        }
        hello.setOnClickListener(click)

        // 위의 3가지 방식 중 마지막 Listener가 실행됨


        // View를 조작하는 함수
        // 1. setText()
        // hello.setText("안녕하세요")
        // 2. setImageResource()
        // image.setImageResource(R.drawable.solid)

    }
}
