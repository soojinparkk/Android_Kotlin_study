package com.example.todolist

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

        var id = intent.getLongExtra("id", -1L)
        if (id == -1L)
            insertMode()
        else
            updateMode(id)

        // calendarView 날짜 선택했을 때 Calendar 객체에 설정
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

    }

    override fun onDestroy() {  // 액티비티가 소멸되는 생명주기
        super.onDestroy()
        realm.close()   // 사용이 끝난 인스턴스 해제
    }

    private fun insertMode() {
        deleteFab.hide()    // 삭제 버튼 감추기

        // 완료 버튼 클릭하면 data 추가
        doneFab.setOnClickListener {
            insertTodo()
        }
    }

    private fun updateMode(id: Long) {
        // id에 해당하는 객체 화면에 표시
        val updateTodo = realm.where<Todo>().equalTo("id", id).findFirst()!!
        todoEditText.setText(updateTodo.title)
        calendarView.date = updateTodo.date

        // 완료 버튼 클릭하면 data 수정 완료
        doneFab.setOnClickListener {
            updateTodo(id)
        }

        // 삭제 버튼 클릭하면 해당 객체 삭제
        deleteFab.setOnClickListener {
            deleteTodo(id)
        }
    }

    private fun insertTodo() {
        realm.beginTransaction()    // 트랙잭션 시작

        // Todo 타입의 Realm 객체 생성
        // 이때 @PrimaryKey = nextId()
        val newItem = realm.createObject<Todo>(nextId())
        newItem.title = todoEditText.text.toString()
        newItem.date = calendar.timeInMillis

        realm.commitTransaction()   // 트랙잭션 종료 커밋

        // 다이얼로그 표시
        alert ("CREATE 완료"){
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

    private fun updateTodo(id: Long) {
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

    private fun deleteTodo(id: Long) {
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