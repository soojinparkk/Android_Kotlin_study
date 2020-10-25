package com.example.mygallery

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.util.ArrayList
import kotlin.concurrent.timer

private const val REQUEST_READ_EXTERNAL_STORAGE = 1000    // 권한 요청에 대한 결과를 분기 처리하는 데 사용

class MainActivity : AppCompatActivity() {

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
        val query = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,      // 가져올 항목 배열
            null,       // 조건
            null,   // 조건
            "${MediaStore.Images.ImageColumns.DATE_ADDED} DESC")    // 찍은 날짜 내림차순

        val fragments = mutableListOf<Fragment>()

        // scoped storage 대응
        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                fragments.add(PhotoFragment.newInstance(contentUri))
            }
        }

        /*
        if (query != null) {
            while (query.moveToNext()) {
                // 사진 경로 uri 가져오기
                val uri = query.getString(query.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                Log.d("test", uri)

                // 사진을 cursor 객체로부터 가져올 때마다 프래그먼트 생성 후 리스트에 추가
                fragments.add(PhotoFragment.newInstance(uri))
            }
            // cursor 객체를 더 이상 사용하지 않을 때는 close() 메서드로 닫아야 함
            // 닫지 않으면 메모리 누수가 발생함
            // 매모리 누수: 메모리가 해제되지 않는 상황이 지속되는 것
            // 메모리 누수가 쌓이면 잘 동작하던 폰이 느려지고 앱이 죽을 수 있음
            query.close()
        }
        */

        val adapter = MyPagerAdapter(supportFragmentManager)
        adapter.updateFragments(fragments)
        viewPager.adapter = adapter

        // 3초마다 자동 슬라이드
        timer(period = 3000) {
            runOnUiThread {
                if (viewPager.currentItem < adapter.itemCount - 1) {    // 현재 페이지가 마지막 페이지가 아닐경우
                    viewPager.currentItem = viewPager.currentItem + 1
                } else {    // 현재 페이지가 마지막 페이지일 경우
                    viewPager.currentItem = 0
                }
            }
        }

    }
}