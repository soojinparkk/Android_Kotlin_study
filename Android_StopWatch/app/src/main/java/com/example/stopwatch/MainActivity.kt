package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0    // 시간을 계산할 time 변수 초기화
    private var isRunning = false   // 타이머가 동작 중인지 아닌지 저장하는 변수
    private var timerTask: Timer? = null    // timer 취소를 위해 변수 선언
                                            // timer를 실행하고 반환하는 Timer 객체를 저장
    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playFab.setOnClickListener {
            isRunning = !isRunning

            if (isRunning)
                start()
            else
                pause()
        }

        lapBtn.setOnClickListener {
            recordLapTime()
        }

        resetFab.setOnClickListener {
            reset()
        }

    }

    private fun pause() {
        playFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timerTask?.cancel()
    }

    private fun start() {
        playFab.setImageResource(R.drawable.ic_baseline_pause_24)

        // timer: 일정한 시간을 주기로 반복하는 동작을 수행할 때 사용
        // 메인 스레드: UI를 조작함
        // 워커 스레드: 오래 걸리는 작업을 보이지 않는 곳에서 처리함
        // timer는 워커 스레드에서 동작 -> UI 조작 X
        timerTask = timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100

            // UI 조작 가능하게 만드는 메소드
            runOnUiThread {
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    private fun recordLapTime() {
        val lapTime = time     // this.time
        val textView = TextView(this)
        textView.text = "$lap LAP : ${lapTime / 100}.${lapTime % 100}"

        // LinearLayout의 맨 위에 textView 추가
        // addView(): 두 번째 인자에 인덱스 값 지정하면 해당 위치에 뷰 추가됨
        lapLayout.addView(textView, 0)
        lap++
    }

    private fun reset() {
        timerTask?.cancel()

        // 모든 변수 초기화
        time = 0
        isRunning = false
        playFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        secTextView.text = "0"
        milliTextView.text = "00"

        // 모든 랩타임 제거
        lapLayout.removeAllViews()
        lap = 1
    }
}