package com.example.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View
import android.view.ViewDebug

class TiltView(context: Context?) : View(context) {
    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    private var cX: Float = 0f
    private var cY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    init {
        // 녹색 페인트
        greenPaint.color = Color.GREEN

        // 검은색 테두리 페인트
        blackPaint.style = Paint.Style.STROKE
    }

    // 뷰의 크기가 결정되면 해당 메서드로
    // 중점 좌표 (cX, cY) 계산
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w / 2f
        cY = h / 2f
    }

    // 인자로 넘어오는 canvas 객체에 뷰의 모습을 그림
    override fun onDraw(canvas: Canvas?) {
        canvas?.drawCircle(cX, cY, 100f, blackPaint)    // 바깥 테두리 원
        canvas?.drawCircle(xCoord + cX, yCoord + cY, 100f, greenPaint)    // 녹색 원

        // 가운데 십자가
        canvas?.drawLine(cX - 20, cY, cX + 20, cY, blackPaint)
        canvas?.drawLine(cX, cY - 20, cX, cY + 20, blackPaint)
    }

    // 액티비티에서 센서값이 변경될 때마다 해당 메서드 호출되도록 설정함
    fun onSensorEvent(event: SensorEvent) {
        // 화면을 가로로 돌렸으므로 X축과  Y축을 서로 바꿈
        // -> 이해하기 편함
        // 센서값에 각각 20을 곱한 이유:
        // 그대로 좌표로 사용하면 범위가 너무 적어서 녹색원의 움직임을 알아보기 어려움
        // 적당한 값을 곱해 보정하여 녹색원이 이동하는 범위를 넓힘
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20

        // 뷰의 onDraw() 메서드를 다시 호출하는 메서드
        invalidate()
    }
}