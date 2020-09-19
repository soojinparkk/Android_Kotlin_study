package com.example.todolist

import android.app.Application
import io.realm.Realm

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}

// 앱이 실행될 때 제일 먼저 Realm 초기화
// 앱을 실행하면 가장 먼저 실행되는 Application 객체를 상속받음
// -> 전체 activity에서 공통 사용하는 객체를 초기화할 때
//          이렇게 manifests에 name 추가해서 사용