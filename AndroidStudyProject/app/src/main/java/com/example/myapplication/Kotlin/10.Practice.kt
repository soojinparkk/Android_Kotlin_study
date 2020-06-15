package com.example.myapplication.Kotlin

fun main(args:Array<String>) {

    val value: Int? = null
    when (value) {
        null -> println("value is null")
        else -> println("value is not null")
    }

    val value2: Boolean? = null
    // 모든 경우의 수에 대해 경우를 선언해줘야 함 (null 포함)
    when (value2) {
        true -> println("true")
        false -> println("false")
        null -> println("value is null")
    }
    
    // 모든 경우에 대해 반드시 값이 리턴될 수 있어야 함
    val value3 = when (value2) {
        true -> 1
        false -> 2
        null -> 3
        // null or else 가능
    }
    println("value3 = $value3")

    val value4: Int = 10
    when (value4) {
        is Int -> println("value4 is Int")
        else -> println("value4 is not Int")
    }

    val value5: Int = 87
    when (value5) {
        in 60 .. 70 -> println("value5 is in 60-70")
        in 70 .. 80 -> println("value5 is in 70-80")
        in 80 .. 90 -> println("value5 is in 80-90")
    }
}