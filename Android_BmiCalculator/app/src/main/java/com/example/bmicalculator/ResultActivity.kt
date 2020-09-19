package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // intent로부터 전달받은 데이터 꺼낸 후 키와 몸무게 저장
        val height = intent.getStringExtra("height").toInt()
        val weight = intent.getStringExtra("weight").toInt()

        // bmi = 몸무게 / {(키 / 100)제곱}
        // weight / Math.pow(height / 100.0, 2.0)
        val bmi = weight / (height / 100.0).pow(2.0)

        // bmi 결과 text로 표시
        when {
            bmi >= 35 -> resultTextView.text = "고도 비만"
            bmi >= 30 -> resultTextView.text = "2단계 비만"
            bmi >= 25 -> resultTextView.text = "1단계 비만"
            bmi >= 23 -> resultTextView.text = "과체중"
            bmi >= 18.5 -> resultTextView.text = "정상"
            else -> resultTextView.text = "저체중"
        }

        // bmi 결과 image로 표시
        when {
            bmi >= 23 ->
                imageView.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)
            bmi >= 18.5 ->
                imageView.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
            else -> imageView.setImageResource(R.drawable.ic_baseline_sentiment_dissatisfied_24)
        }

        // Toast.makeText(this, "$bmi", Toast.LENGTH_LONG).show()
        // toast -> Anko 라이브러리를 사용하면 코드 짧아짐
        toast("$bmi")

    }
}