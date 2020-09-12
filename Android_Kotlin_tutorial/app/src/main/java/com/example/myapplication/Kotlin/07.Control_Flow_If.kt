package com.example.myapplication.Kotlin

// if / else if / else

fun main(args:Array<String>) {
    val a: Int = 5
    val b: Int = 10

    // if / else if / else
    if (a > b) {
        println("a가 b보다 크다 (a = $a)")
    } else if (a < b){
        println("a가 b보다 작다 (b = $b)")
    } else {
        println("a가 b와 같다 (a = $a)")
    }

    // 값을 리턴하는 if
    var max = if (a > b) a else b

    println("max = $max")
}