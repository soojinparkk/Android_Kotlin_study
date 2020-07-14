package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_practice_addview_details.*

class PracticeAddviewDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_addview_details)

        getPersonInfoAndDraw()

        backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    fun getPersonInfoAndDraw () {
        val name = intent.getStringExtra("name")
        val num = intent.getStringExtra("num")

        details_name.setText(name)
        details_number.setText(num)

    }
}
