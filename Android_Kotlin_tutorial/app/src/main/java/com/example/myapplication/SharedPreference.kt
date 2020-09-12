package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreference : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        // MODE
        //  - MODE_PRIVATE: 생성한 application에서만 사용 가능
        //  - MODE_WORLD_READABLE: 다른 application에서 사용 가능 -> Only READ
        //  - MODE_WORLD_WRITEABLE: 다른 application에서 사용 가능 -> READ, WRITE
        //  - MODE_MULTI_PROCESS: 이미 호출되어 사용중인지 체크
        //  - MODE_APPEND: 기존 preference에 신규로 추가


        save_Btn.setOnClickListener {
            // SharedPreference에 저장하는 방법
            val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.putString("hi", "안녕하세요")
            editor.putString("bye", "안녕히가세요")
            editor.commit() // -> 데이터 들어감
        }

        // SharedPreference 값을 불러오는 방법
        load_Btn.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val value1 = sharedPreference.getString("hi", "No DATA")
            val value2 = sharedPreference.getString("bye", "No DATA")

            Log.d("key1", "Value1 = $value1")
            Log.d("key1", "Value2 = $value2")
        }

        delete_Btn.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.remove("hi")
            editor.commit()
        }

        delete_all_Btn.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.clear()
            editor.commit()
        }

    }
}
