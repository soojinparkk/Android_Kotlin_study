package com.example.todolist

import android.icu.text.UnicodeSetSpanner
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton
import java.util.*

class EditActivity : AppCompatActivity() {
    
    var realm = Realm.getDefaultInstance()  // Realm 사용하기 위해 인스턴스 얻음
    val calendar: Calendar = Calendar.getInstance() // 날짜를 다룰 캘린더 객체 얻음
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    }

    override fun onDestroy() {  // 액티비티가 소멸되는 생명주기
        super.onDestroy()
        realm.close()   // 사용이 끝난 인스턴스 해제
    }

    private fun insertTodo() {
        realm.beginTransaction()    // 트랙잭션 시작

        val newItem = realm.createObject<Todo>(nextId())    // 새 객체 생성
        newItem.title = todoEditText.text.toString()
        newItem.date = calendar.timeInMillis

        realm.commitTransaction()   // 트랙잭션 종료 커밋

        // 다이얼로그 표시
        alert ("ADD 완료"){
            yesButton { finish() }
        }.show()
    }

    // 다음 id return
    private fun nextId(): Int {
        val maxId = realm.where<Todo>().max("id")
        if (maxId != null)
            return maxId.toInt() + 1
        return 0
    }

    private fun updateTodo(id: Int) {
        realm.beginTransaction()

        val updateItem = realm.where<Todo>().equalTo("id", id).findFirst()!!    // 값 수정
        updateItem.title = todoEditText.text.toString()
        updateItem.date = calendar.timeInMillis

        realm.commitTransaction()

        // 다이얼로그 표시
        alert ("UPDATE 완료"){
            yesButton { finish() }
        }.show()
    }

    private fun deleteTodo(id: Int) {
        realm.beginTransaction()

        val deleteItem = realm.where<Todo>().equalTo("id", id).findFirst()!!    // 삭제할 객체
        deleteItem.deleteFromRealm()    // 삭제

        realm.commitTransaction()

        // 다이얼로그 표시
        alert ("DELETE 완료"){
            yesButton { finish() }
        }.show()
    }
}