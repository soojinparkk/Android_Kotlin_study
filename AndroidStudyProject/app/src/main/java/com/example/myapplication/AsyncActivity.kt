package com.example.myapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_async.*
import java.lang.Exception
import java.lang.Thread

class AsyncActivity : AppCompatActivity() {

    var task: BackgroundAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        start_Btn.setOnClickListener {
            task = BackgroundAsyncTask(progress_bar, ment)
            task?.execute()
        }

        stop_Btn.setOnClickListener {
            task?.cancel(true)
        }
    }

    // onPause() -> 다른 Activity로 넘어갔을 때 Async도 같이 정지시킴
    override fun onPause() {
        task?.cancel(true)
        super.onPause()
    }
}

class BackgroundAsyncTask (
    val progressbar: ProgressBar,
    val progressText: TextView
): AsyncTask<Int, Int, Int>() {
    // params: DoInBackground 에서 사용할 타입
    // progress: onProgressUpdate 에서 사용할 타입
    // result: onPostExecute 에서 사용할 타입

    var percent: Int = 0

    override fun onPreExecute() {
        percent = 0
        progressbar.setProgress(percent)
    }

    override fun doInBackground(vararg params: Int?): Int {
        while (isCancelled() == false) {
            percent++
            if (percent > 100)
                break
            else
                publishProgress(percent)

            try {
                Thread.sleep(100)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return percent
    }

    override fun onProgressUpdate(vararg values: Int?) {
        progressbar.setProgress(values[0] ?: 0)
        progressText.setText("Percent = "+values[0])

        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Int?) {
        progressText.setText("COMPlETE")
    }

    override fun onCancelled() {
        progressbar.setProgress(0)
        progressText.setText("CANCELED")
    }
}
