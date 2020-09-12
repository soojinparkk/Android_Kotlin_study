package com.example.myapplication.Kotlin

// 1. 전역변수
// 2. 지역변수

var num10: Int = 10

fun main(args:Array<String>) {
    println(num10)
    
    val test = Test("su")
    test.testName()
    println(test.name)
    println(num10)
}

class Test(var name: String) {
    fun testName() {
        println(name)
        name = "suzan"
    }
}