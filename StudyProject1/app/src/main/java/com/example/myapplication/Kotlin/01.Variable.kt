package com.example.myapplication.Kotlin

// 변수 == 상자
// 변수 선언 방법
// var/val 변수명 = 값

var num = 10
var hello = "hello"
var point = 3.3

val fix = 20


fun main(args:Array<String>) {
    println(num)
    println(hello)
    println(point)
    println(fix)

    num = 100
    hello = "bye"
    point = 0.33

    println(num)
    println(hello)
    println(point)
}