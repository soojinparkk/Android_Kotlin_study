package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_thread.*
import java.lang.Thread

class Thread : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        val runnable: Runnable = object : Runnable {
            override fun run() {
                Log.d("thread", "thread-1 is made")
            }
        }

        val thread: Thread = Thread(runnable)

        btn.setOnClickListener {
            thread.start()
        }

        // 1)
        Thread(object : Runnable {
            override fun run() {
                Log.d("thread", "thread-2 is made")
            }
        }).start()

        // 2) 람다 방식
        Thread (Runnable {
            Thread.sleep(2000)
            Log.d("thread", "thread-3 is made")

            // UI를 바꾸는 것은 MainThread만 접근 가능
            // runOnUiThread 사용해야 함
            runOnUiThread {
                btn.setBackgroundColor(getColor(R.color.colorPrimary))
            }
        }).start()
    }
}
