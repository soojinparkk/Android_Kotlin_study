package com.example.myapplication.Kotlin

fun main(args:Array<String>) {

    // 배열 생성 시 처음 만들어진 배열 사이즈 변경 불가
    var array = arrayOf<Int>(1, 2, 3)
    val num = array.get(0)
    println(num)

    // 배열 생성 방법(3)
    val a1 = intArrayOf(1, 2, 3)
    val a2 = charArrayOf('a', 'b', 'c')
    val a3 = doubleArrayOf(1.1, 2.2, 3.3)
    val a4 = booleanArrayOf(true, false)

    // 배열 생성 방법(4)
    // lambda 활용한 방법
    var a5 = Array(10, { 0 })
    var a6 = Array(5, {1;2;3;4;5})

    val num2 = a6[1]
    println("a6[1] = $num2")
}