package com.example.todolist

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Todo (
    @PrimaryKey var id: Long = 0,
    var title: String = "",
    var date: Long = 0
): RealmObject()

// Realm에서 사용하는 class에 open 키워드 추가
// @PrimaryKey: Realm에서 제공하는 기본키 제약 주석
// RealmObject 클래스 상속받아 Realm DB에서 다룸