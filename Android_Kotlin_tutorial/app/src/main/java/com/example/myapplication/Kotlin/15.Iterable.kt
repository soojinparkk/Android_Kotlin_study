package com.example.myapplication.Kotlin

fun main(args:Array<String>) {

    val a = mutableListOf<Int>(2, 3, 4, 5, 6, 7, 8, 9, 0)

    // 반복하는 방법(1)
    for (item in a) {
        if (item == 5)
            println("item is Five")
        else
            println("item is $item")
    }

    // 반복하는 방법(2)
    for ((index, item) in a.withIndex()) {
        println("index:$index value:$item")
    }

    // 반복하는 방법(3) -> Lambda
    a.forEach {
        println(it)
    }

    a.forEach { item ->
        println(item)
    }

    // 반복하는 방법(4) -> Lambda
    a.forEachIndexed { index, item ->
        println("index:$index value:$item")
    }

    // 반복하는 방법(5)
    // 0부터 a.size - 1 까지 반복
    // until -> 마지막 포함X
    for (i in 0 until a.size) {
        println(a.get(i))
    }

    for (i in 0 until a.size step (2)) {
        println(a.get(i))
    }

    // 8부터 0까지 반복
    for (i in a.size-1 downTo (0) step (2)) {
        println(a.get(i))
    }

    // .. -> 마지막 포함
    for (i in 0..a.size) {
        println(i)
    }


    // 반복하는 방법(6)
    var x: Int = 0
    var y: Int = 4

    while (x < y) {
        x++
        print("x ")
    }

    // 반복하는 방법(7)
    do {
        println("hi")
    } while (x < y)
}