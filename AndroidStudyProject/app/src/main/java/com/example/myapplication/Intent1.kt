package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intent.*

class Intent1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        change_activity.setOnClickListener {
            // Key, Value 방식 -> 쌍으로 만들어 저장 (Dictionary)
            // 명시적 Intent
            // 1.
//            val intent = Intent(this@Intent1, Intent2::class.java)
//            intent.putExtra("num1", 1)
//            intent.putExtra("num2", 2)
//            startActivity(intent)

            // 2. Apply
//            val intent2 = Intent(this@Intent1, Intent2::class.java)
//            intent2.apply {
//                this.putExtra("num1", 1)
//                this.putExtra("num2", 2)
//            }
//            startActivityForResult(intent2, 200)

            // 암시적 Intent
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"))
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200) {
            Log.d("number", "" + requestCode)
            Log.d("number", "" + resultCode)
            val result = data?.getIntExtra("result", 0)
            Log.d("number", "" + result)
        }

    }
}
