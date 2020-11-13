package com.example.xylophone

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // 안드로이드 5.0 (LOLLIPOP) 기준으로 분기 처리
    // 현재 사용하고 있는 버전의 경우 분기 처리할 필요는 없지만
    // 구 버전의 안드로이드 기기를 사용했을 경우에 대비
    private val soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        SoundPool.Builder().setMaxStreams(8).build()
    } else {
        SoundPool(8, AudioManager.STREAM_MUSIC, 0)
    }

    // 텍스트 뷰의 ID와 음원 파일의 리소스 ID 연관 지은 Pair 객체 생성
    // Pair 클래스는 두 개의 연관된 객체 저장
    private val sounds = listOf(
        Pair(R.id.do1, R.raw.do1),
        Pair(R.id.re, R.raw.re),
        Pair(R.id.mi, R.raw.mi),
        Pair(R.id.fa, R.raw.fa),
        Pair(R.id.sol, R.raw.sol),
        Pair(R.id.la, R.raw.la),
        Pair(R.id.si, R.raw.si),
        Pair(R.id.do2, R.raw.do2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰가 여러 개일 때는 forEach() 함수 등을 사용하여
        // 동적으로 클릭 이벤트 구현 가능
        sounds.forEach { tune(it) }
    }

    private fun tune(pitch: Pair<Int, Int>) {
        val soundId = soundPool.load(this, pitch.second, 1)
        findViewById<TextView>(pitch.first).setOnClickListener {
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }

    // 반드시 release() 메서드 호출하여
    // SoundPool 객체의 자원 해제
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}