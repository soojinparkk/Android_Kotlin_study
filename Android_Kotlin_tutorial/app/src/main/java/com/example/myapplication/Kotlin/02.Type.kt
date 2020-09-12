package com.example.myapplication.Kotlin

// 정수형: Long > Int > Short > Byte
// 실수형: Double > Float
// 문자: Char
// 문자열: String
// 논리형: Boolean

// var/val 변수 : 자료형 = 값
// 변수를 선언할 때 자료형을 입력하지 않으면 자동 할당

// Variable or Value ??
// -1. 변하지 않는 값이라면 Value
// -2. 변할지 변하지 않을 지 모르겠는 경우 Value

var number = 10
var number1 : Int = 20
var hello1 : String = "hello"
var point1 : Double = 3.3

fun main(args:Array<String>) {
    number = 20
    println(number)
}