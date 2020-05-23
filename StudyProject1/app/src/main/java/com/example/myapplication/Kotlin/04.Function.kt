package com.example.myapplication.Kotlin

// 함수
// = 어떤 input 을 넣어주면 output 나오는 것

// 함수 선언 방법
// fun 함수명 (변수명 : 타입, 변수명 : 타입,,,) : 반환형 {
//      함수 내용
//      return 반환값
// }

fun plus(first: Int, second: Int): Int {
    val result: Int = first + second
    return result
}

// 디폴트 값을 갖는 함수
fun plusFive(first: Int, second: Int = 5): Int {
    val result: Int = first + second
    return result
}

// 반환값이 없는 함수
fun printPlus(first: Int, second: Int): Unit {
    val result: Int = first + second
    println("$result Unit 생략 가능 ")
}

// 간단하게 함수 선언하는 방법
fun plusShort(first: Int, second: Int) = first + second

// 가변 인자를 받는 함수 선언하는 방법
// vararg: 한개부터 N개까지 여러개 인자 받기 가능
fun plusMany(vararg numbers: Int) {
    for (num in numbers) {
        println(num)
    }
}

fun main(args: Array<String>) {
    val result = plus(5, 10)
    println(result)

    val result2 = plus(second = 100, first = 50)
    println(result2)

    // 디폴트 값을 갖는 함수 출력
    val result3 = plusFive(10, 20)
    println(result3)
    val result4 = plusFive(10)
    println(result4)

    // 반환값이 없는 함수 출력
    printPlus(100, 200)

    // 축약된 함수 출력
    val result5 = plusShort(1000, 2000)
    println(result5)

    //가변 인자 받는 함수 출력
    plusMany(1, 2, 3)
}