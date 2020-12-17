package com.example.sqlite

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
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

            textView.text = " insert complete"
        }

        btn2.setOnClickListener {
            var helper: DBHelper = DBHelper(this)
            var db: SQLiteDatabase = helper.writableDatabase

            var sql = "select * from TestTable"

            // Cursor class type의 객체
            // select한 데이터에 접근해서 데이터 가져옴
            // select -> db.rawQuery()
            // insert, update, delete -> db.execSQL()
            var c: Cursor = db.rawQuery(sql, null)

            textView.text = ""

            while (c.moveToNext()) {
                // column name에 따른 해당 값 index 번호 반환
                var idx_pos = c.getColumnIndex("idx")
                var textData_pos = c.getColumnIndex("textData")
                var intData_pos = c.getColumnIndex("intData")
                var floatData_pos = c.getColumnIndex("floatData")
                var dateData_pos = c.getColumnIndex("dateData")

                var idx = c.getInt(idx_pos)
                var textData = c.getString(textData_pos)
                var intData = c.getInt(intData_pos)
                var floatData = c.getDouble(floatData_pos)
                var dateData = c.getString(dateData_pos)

                textView.append("idx: $idx\n")
                textView.append("textData: $textData\n")
                textView.append("intData: $intData\n")
                textView.append("floatData: $floatData\n")
                textView.append("dateData: $dateData\n\n")
            }

            db.close()
        }

        btn3.setOnClickListener {
            var helper = DBHelper(this)
            var db = helper.writableDatabase

            var sql = "update TestTable set textData=? where idx=?"
            var args = arrayOf("arg3", "1")     // 쿼리문에 작성되어 있는 물음표 순서에 맞춰서 setting

            db.execSQL(sql, args)

            db.close()

            textView.text = "update complete"
        }

        btn4.setOnClickListener {
            var helper = DBHelper(this)
            var db = helper.writableDatabase

            var sql = "delete from TestTable where idx=?"
            var args = arrayOf("1")

            db.execSQL(sql, args)

            db.close()

            textView.text = "delete complete"
        }

    }
}