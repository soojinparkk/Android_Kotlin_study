package com.example.myapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_network.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkTask(
            network_recyclerview,
            LayoutInflater.from(this)
        ).execute()
    }
}

// MainThread 정지 시킬 수 X -> 네트워크는 반드시 AsyncTask 필요
// 네트워크를 MainThread에서 사용 -> ERROR

class NetworkTask(
    val recyclerview: RecyclerView,
    val inflater: LayoutInflater
): AsyncTask<Any?, Any?, Array<PersonFromServer>>() {

    override fun doInBackground(vararg params: Any?): Array<PersonFromServer> {
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

        val data = Gson().fromJson(buffer, Array<PersonFromServer>::class.java)

        return data
    }

    // UI thread에 접근 가능하기 때문에 여기에서 뷰를 그림
    override fun onPostExecute(result: Array<PersonFromServer>?) {
       val adapter = PersonAdapter(result!!, inflater)
        recyclerview.adapter = adapter
        super.onPostExecute(result)
    }
}

// 실제로 날아오는 데이터 -> byte 단위
// UTF-8 표준 방식, BufferedReader 사용

class PersonAdapter (
    val personList: Array<PersonFromServer>,
    val inflater: LayoutInflater
): RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        val name: TextView
        val age: TextView
        val intro: TextView

        init {
            name = itemview.findViewById(R.id.person_name)
            age = itemview.findViewById(R.id.person_age)
            intro = itemview.findViewById(R.id.person_ment)
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view_network, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(personList.get(position).name ?: "")
        holder.age.setText(personList.get(position).age.toString())
        holder.intro.setText(personList.get(position).intro ?: "")
    }
}

