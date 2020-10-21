package com.example.mygallery

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {
    private val REQUEST_READ_EXTERNAL_STORAGE = 1000    // 권한 요청에 대한 결과를 분기 처리하는 데 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 권한이 부여되었는지 확인
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.
            PERMISSION_GRANTED) {

            // 권한이 허용되지 않음
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // 1. 이전에 권한 요청을 거부했을 경우
                // 권한이 왜 필요한지 별도의 메세지 설정
                alert("사진 정보를 얻으려면 외부 저장소 권한이 필수로 필요합니다",
                    "권한이 필요한 이유") {
                    yesButton {
                        // 권한 요청
                        ActivityCompat.requestPermissions(this@MainActivity,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_READ_EXTERNAL_STORAGE)
                    }
                    noButton {  }
                }.show()
            } else {
                // 2. 권한이 부여되지 않았을 경우
                // 권한 요청
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERNAL_STORAGE)
            }
        } else {
            // 권한이 이미 허용됨
            getAllPhotos()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_READ_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty()
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // 권한 허용됨
                    getAllPhotos()
                } else {
                    // 권한 거부됨
                    toast("권한 거부됨")
                }
                return
            }
        }
    }

    private fun getAllPhotos() {
        // 모든 사진 정보 가져오기
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,      // 가져올 항목 배열
            null,       // 조건
            null,   // 조건
            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")    // 찍은 날짜 내림차순

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // 사진 경로 uri 가져오기
                val uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                Log.d("test", uri)
            }
            // cursor 객체를 더 이상 사용하지 않을 때는 close() 메서드로 닫아야 함
            // 닫지 않으면 메모리 누수가 발생함
            // 매모리 누수: 메모리가 해제되지 않는 상황이 지속되는 것
            // 메모리 누수가 쌓이면 잘 동작하던 폰이 느려지고 앱이 죽을 수 있음
            cursor.close()
        }
    }
}