package com.example.myapplication.Kotlin

// 배열이 필요한 이유
//  - group이 필요할 때

fun main (args:Array<String>) {

    // 배열 생성 방법(1)
    var group = arrayOf<Int>(1, 2, 3, 4, 5)
    println(group is Array)

    // 배열 생성 방법(2)
    // type 지정하지 않으면 여러 type의 배열 사용 가능
    var group2 = arrayOf(1, 2, 3.5, "hi")


    // 배열의 값을 꺼내는 방법
    val test = group.get(0)
    val test2 = group2[3]
    println("group의 0번째 = $test")
    println("group2의 3번째 = $test2")

    // 배열의 값을 바꾸는 방법
    group.set(0, 100)
    println(group[0])

    group[0] = 200
    println(group[0])
}