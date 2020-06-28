package com.example.myapplication.Kotlin

// 인터페이스도 구현이 있는 함수 생성 가능
// 인터페이스의 구현된 함수는 인터페이스를 구현하는 클래스에서
// 그 함수를 구현할 필요 없음

// abstract: 반드시 구현해야 하는 함수

fun main(args:Array<String>) {
    val student = Student__()
    student.sleep()
    student.eat()
    student.study()
}

interface Person__ {
    fun eat() {
        println("eat")
    }
    fun sleep() {
        println("sleep")
    }
    abstract fun study()
}

class Student__: Person__ {
    override fun study() {
        println("student study")
    }
}

class Teacher__: Person__ {
    override fun study() {
        println("teacher study")
    }
}