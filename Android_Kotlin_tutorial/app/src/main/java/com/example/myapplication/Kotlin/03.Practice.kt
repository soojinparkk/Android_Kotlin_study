package com.example.myapplication.Kotlin

var a = 1 + 2 + 3
var b = "1"
var c = b.toInt()
var d = b.toFloat()

var e = "John"
var f = "My name is $e! Nice to meet you"

// Null
// = 존재 하지 않는다
// 자료형 뒤에 ?를 붙여주면 Null 을 가질 수 있는 자료형
var abc : Int? = null

fun main (args: Array<String>) {
    println(a)
    println(b)
    println(c)
    println(d)
    println(f)
    println(abc)
}