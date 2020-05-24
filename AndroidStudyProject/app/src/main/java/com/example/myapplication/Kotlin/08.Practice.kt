package com.example.myapplication.Kotlin

fun main(args:Array<String>) {
    val a: Int? = null
    val b: Int = 10
    val c: Int = 100

    if (a == null)
        println("a is null")
    else
        println("a is not null")

    if (b + c == 110)
        println("b plus c is 110")
    else
        println("b plus c is not 110")

    // 엘비스 연산자 -> ?:
    // num = null 경우, ?: 뒤의 숫자 대입
    // num != null, n = 100 경우, 100 대입
    // null에 대응하기 위한 연산자이기 때문에 꼭 필요

    var num: Int? = null
    val num2 = num ?: 10
    num = 100
    val num3 = num ?: 10
    println("num = null -> num2 = $num2")
    println("num != null -> num3 = $num3")
}