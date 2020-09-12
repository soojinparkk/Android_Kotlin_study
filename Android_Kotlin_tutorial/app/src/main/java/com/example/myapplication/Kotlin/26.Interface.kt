package com.example.myapplication.Kotlin

// Interface
// = 약속
//     (이것을 구현하면 구현한 클래스도 인터페이스 타입임)
// 인터페이스를 구현하는 클래스는 인터페이스 안 함수들 반드시 구현
// 그 함수들에 내용을 채워줘야 함 (정확한 기능 설명 필요)
// - 생성자가 없음 -> 인스턴스화 시킬 수 없음 -> 설명서 아님
// - 지침서 -> 구현하고 싶다면 반드시 아래 기능을 구현해야 함

fun main(args:Array<String>) {
    val student_: Student_ = Student_()
    student_.eat()
    student_.sleep()
}

interface Person_ {
    fun eat()
    fun sleep()
}

class Student_: Person_ {
    override fun eat() {

    }

    override fun sleep() {

    }
}

class SoccerPlayer: Person_ {
    override fun eat() {

    }

    override fun sleep() {

    }
}

open class Person() {
    fun eat() {

    }
    fun sleep() {

    }
}

class Student(): Person() {

}

class Teacher(): Person() {

}