package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_resource.*

class Resource : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        // 1)
        val ment = resources.getString(R.string.hello)
        Log.d("ment", "ment: " + ment)

        // 2)
        val ment2 = getString(R.string.hello)

        // SDK version 23 이상만 가능 -> 아닐경우 분기문으로 처리
        val color = getColor(R.color.textview_color)

        // btn color 변경
        btn1.setBackgroundColor(getColor(R.color.textview_color))
    }
}
