package com.example.contentprovider

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
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
            // 해당 이름을 가진 content provider 사용 요청
            val uri = Uri.parse("content://kr.co.soo.dbprovider")
            var c: Cursor? = contentResolver.query(uri, null, null, null, null)

            textView.text = ""

            while (c!!.moveToNext()) {
                // column name에 따른 해당 값 index 번호 반환
                var idxPos = c.getColumnIndex("idx")
                var textDataPos = c.getColumnIndex("textData")
                var intDataPos = c.getColumnIndex("intData")
                var floatDataPos = c.getColumnIndex("floatData")
                var dateDataPos = c.getColumnIndex("dateData")

                var idx = c.getInt(idxPos)
                var textData = c.getString(textDataPos)
                var intData = c.getInt(intDataPos)
                var floatData = c.getDouble(floatDataPos)
                var dateData = c.getString(dateDataPos)

                textView.append("idx: $idx\n")
                textView.append("textData: $textData\n")
                textView.append("intData: $intData\n")
                textView.append("floatData: $floatData\n")
                textView.append("dateData: $dateData\n\n")
            }
        }

        btn2.setOnClickListener {
            var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            var date = sdf.format(Date())

            // ContentValues(): 어떤 column에 어떤 값이 대응하는 지 지정 (= 일대일 대응)
            var cv1 = ContentValues()
            cv1.put("textData", "new")
            cv1.put("intData", 100)
            cv1.put("floatData", 11.11)
            cv1.put("dateData", date)

            var cv2 = ContentValues()
            cv2.put("textData", "newnew")
            cv2.put("intData", 200)
            cv2.put("floatData", 22.22)
            cv2.put("dateData", date)

            val uri = Uri.parse("content://kr.co.soo.dbprovider")
            contentResolver.insert(uri, cv1)
            contentResolver.insert(uri, cv2)

            textView.text = "insert complete"
        }

        btn3.setOnClickListener {
            var cv = ContentValues()
            cv.put("textData", "hh")
            var where = "idx=?"
            var args = arrayOf("3")

            val uri = Uri.parse("content://kr.co.soo.dbprovider")
            contentResolver.update(uri, cv, where, args)

            textView.text = "update complete"
        }

        btn4.setOnClickListener {
            var where = "idx=?"
            var args = arrayOf("3")

            val uri = Uri.parse("content://kr.co.soo.dbprovider")
            contentResolver.delete(uri, where, args)

            textView.text = "delete complete"
        }

    }
}