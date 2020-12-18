package com.example.file

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {

    var permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    var path: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 외부 저장소의 경로 얻어옴
        // 해당 경로의 폴더에 데이터를 저장하면
        // 어플리케이션 삭제 시 같이 삭제가 되며 그 외의 폴더는 유지됨
        // 에뮬레이터 내의 Files에서 확인 가능
        // Files - 우측 상단 옵션 메뉴 - show internal storage 클릭
        path = Environment.getExternalStorageDirectory().absolutePath + "/android/data/" + packageName

        checkPermission()

        var file = File(path)
        // path에 해당하는 디렉토리가 없을 경우 확인
        // 디렉토리가 없을 경우 생성
        if (!file.exists())
            file.mkdir()

        // 내부 저장소에 데이터 저장하기
        btn1.setOnClickListener {
            try {
                var output = openFileOutput("myFile.dat", MODE_PRIVATE)
                var dos = DataOutputStream(output)
                dos.writeInt(100)
                dos.writeDouble(33.33)
                dos.writeUTF("Hi")
                dos.flush()
                dos.close()
                textView.text = "complete"
            } catch (e: Exception) {
                e.printStackTrace()     // 예외 처리
            }
        }

        // 내부 저장소에 저장되어 있는 값 읽어오기
        btn2.setOnClickListener {
            try {
                var input = openFileInput("myFile.dat")
                var dis = DataInputStream(input)
                var value1 = dis.readInt()
                var value2 = dis.readDouble()
                var value3 = dis.readUTF()
                dis.close()

                textView.text = "value1: ${value1}\n"
                textView.append("value2: ${value2}\n")
                textView.append("value3: ${value3}\n")
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }

        // 설정된 path의 외부 저장소에 데이터 저장하기
        btn3.setOnClickListener {
            try {
                var output = FileOutputStream(path + "/sdFile.dat")
                var dos = DataOutputStream(output)
                dos.writeInt(200)
                dos.writeDouble(55.55)
                dos.writeUTF("Hello")
                dos.flush()
                dos.close()
                textView.text = "complete"
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // 설정된 path의 외부 저장소에 저장되어 있는 값 읽어오기
        btn4.setOnClickListener {
            try {
                var input = FileInputStream(path + "/sdFile.dat")
                var dis = DataInputStream(input)
                var value1 = dis.readInt()
                var value2 = dis.readDouble()
                var value3 = dis.readUTF()
                dis.close()

                textView.text = "value1: ${value1}\n"
                textView.append("value2: ${value2}\n")
                textView.append("value3: ${value3}\n")
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }

    }

    // 외부 저장소 사용 시 권한 필요
    // 외부 저장소 접근 권한 체크하는 함수
    private fun checkPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return
        for (permission: String in permissionList) {
            var chk = checkCallingOrSelfPermission(permission)
            if (chk == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissionList, 0)
                break
            }
        }
    }

}

