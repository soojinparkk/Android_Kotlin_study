package com.example.myapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkTask().execute()
    }
}

// MainThread 정지 시킬 수 X -> 네트워크는 반드시 AsyncTask 필요
// 네트워크를 MainThread에서 사용 -> ERROR

class NetworkTask(): AsyncTask<Any?, Any?, Any?>() {
    override fun doInBackground(vararg params: Any?): Any? {
        val urlString: String = "http://mellowcode.org/json/students/"
        // url 객체로 변환
        val url: URL = URL(urlString)
        // connection 요청
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        // Header 작성
        connection.setRequestProperty("Content-Type", "application/json")

        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader (
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
        }

        val temp = buffer.split(',')

        return null
    }
}

// 실제로 날아오는 데이터 -> byte 단위
// UTF-8 표준 방식, BufferedReader 사용