package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

class Torch(context: Context) {
    private var cameraId: String? = null
    // getSystemService(): 안드로이드 시스템에서 제공하는 각종 서비스를 관리하는 매니저 클래스 생성
    private var cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    init {
        cameraId = getCameraId()    // 기기에 내장된 카메라마다 부여된 고유한 카메라 ID
    }

    fun flashOn() {
        cameraManager.setTorchMode(cameraId!!, true)
    }

    fun flashOff() {
        cameraManager.setTorchMode(cameraId!!, false)
    }

    private fun getCameraId(): String? {
        // CameraManager: 기기가 가지고 있는 모든 카메라에 대한 정보 목록 제공
        val cameraIds = cameraManager.cameraIdList

        // 각 ID별로 세부 정보를 가지는 객체 얻음
        for (id in cameraIds) {
            val info = cameraManager.getCameraCharacteristics(id)
            val flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)   // 플래시 가능 여부
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING)                // 카메라 렌즈의 방향

            // 플래시가 사용 가능하고, 카메라가 기기의 뒷면을 향하고 있는 카메라 ID를 찾았다면 ID 리턴
            if (flashAvailable != null
                && flashAvailable
                && lensFacing != null
                && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                return id
            }
        }
        // 해당하는 카메라 ID를 찾지 못했다면 null 리턴
        // 에뮬레이터의 경우 카메라가 없기 때문에 null 리턴
        return null
    }
}