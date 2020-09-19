package com.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultBtn.setOnClickListener {
            /*
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height", heightEditText.text.toString())
            intent.putExtra("weight", weightEditText.text.toString())
            startActivity(intent)
            */
            // 액티비티를 전환할 때마다 intent 객체 생성해야 함
            // intent -> Anko 라이브러리를 사용하면 코드 짧아짐

            // intent에 데이터를 담아 전달
            startActivity<ResultActivity>(
                "height" to heightEditText.text.toString(),
                "weight" to weightEditText.text.toString()
            )
        }

    }
}