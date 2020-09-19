package com.example.bmicalculator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()  // 이전에 입력한 값 불러옴

        resultBtn.setOnClickListener {

            // 마지막에 입력한 데이터 저장
            saveData(heightEditText.text.toString().toInt(), weightEditText.text.toString().toInt())

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

    private fun saveData(height: Int, weight: Int) {
        val sp = getSharedPreferences("sp", Context.MODE_PRIVATE)   // SharedPreferences 객체 생성
        val editor = sp.edit()
        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

    private fun loadData() {
        val sp = getSharedPreferences("sp", Context.MODE_PRIVATE)   // SharedPreferences 객체 생성
        val height = sp.getInt("KEY_HEIGHT", 0)
        val weight = sp.getInt("KEY_WEIGHT", 0)

        // 저장된 값이 있다면 저장된 값으로 설정
        if (height != 0 && weight != 0) {
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}