package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class NullSafety : AppCompatActivity() {

    lateinit var lateNum: Late

    class Late(var number: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_null_safety)

        val num: Int = 10
        val num1: Int? = null

        val num2 = num1?.plus(num)
        // num2 = null

        // 삼항연산자 -> 엘비스 연산자 (?:)
        // num1이 null이면 num3 = 10
        val num3 = num1 ?: 10
        
        // lateinit 사용
        lateNum = Late(10)
        Log.d("number", "lateNum: "+lateNum.number)
    }

    fun plus(a: Int, b: Int?): Int? {
        return b?.plus(a)
    }
}
