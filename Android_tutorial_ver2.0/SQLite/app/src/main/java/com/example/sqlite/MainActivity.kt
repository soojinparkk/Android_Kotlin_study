package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            var helper = DBHelper(this)

            // DB에 접근해서 사용할 수 있는 객체 얻어옴 == open
            var db = helper.writableDatabase

            var sql = "insert into TestTable (textData, intData, floatData, dateData) values (?, ?, ?, ?)"

            // 해당 양식에 맞춰서 문자열로 반환
            var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            var date = sdf.format(Date())

            var arg1 = arrayOf("arg1", "100", "11.11", date)
            var arg2 = arrayOf("arg2", "200", "22.22", date)

            // 쿼리 실행
            db.execSQL(sql, arg1)
            db.execSQL(sql, arg2)

            db.close()

            textView.text = "complete"
        }

    }
}