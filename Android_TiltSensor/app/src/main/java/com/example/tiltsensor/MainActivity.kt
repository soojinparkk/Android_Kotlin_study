package com.example.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {

    // sensorManager 변수를 처음 사용할 때
    // getSystemService() 메서드로 SensorManager 객체를 얻음
    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    // 센서값이 변경되면 호출
    // values[0]: x축 값 -> 위로 기울이면 -10~0, 아래로 기울이면 0~10
    // values[1]: y축 값 -> 왼쪽으로 기울이면 -10~0, 오른쪽으로 기울이면 0~10
    // values[2]: z축 값 -> 미사용
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            Log.d("sensor", "onSensorChanged x: " +
                    "${event.values[0]}, y: ${event.values[1]}, z: ${event.values[2]}")

            tiltView.onSensorEvent(event)
        }
    }

    // 센서 정밀도가 변경되면 호출
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    private lateinit var tiltView: TiltView

    override fun onCreate(savedInstanceState: Bundle?) {
        // 화면 꺼지지 않게 하기 (절전 기능 비활성화)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // 화면 가로 모드로 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)

        tiltView = TiltView(this)
        setContentView(tiltView)
    }

    // 액티비티가 동작할 때만 센서가 동작해야 베터리를 아낄 수 있음
    // 일반적으로 센서의 사용 등록 -> onResume()
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    // 액티비티가 동작 중일 때만 센서를 사용하기 위해
    // 화면이 꺼지기 직전인 onPause() 메서드에서 센서 해제
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}