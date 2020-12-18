package com.example.file

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.DataInputStream
import java.io.DataOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 내부 저장소에 저장하기
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


    }
}