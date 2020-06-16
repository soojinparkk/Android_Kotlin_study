package com.example.myapplication.Kotlin

// Collection
// -> list, set, map

fun main(args:Array<String>) {

    // Immutable Collections -> 값 변경 불가
    // List
    val numberList = listOf<Int>(1, 2, 3, 3, 3)
    println(numberList)
    println(numberList.get(0))

    // Set = 집합
    // 중복된 값을 허용하지 않음
    // 순서가 없음 -> 인덱스 없음 (인덱스 사용 불가)
    val numberSet = setOf<Int>(1, 2, 3, 3, 3)
    println(numberSet)
    numberSet.forEach {
        println(it)
    }

    // Map
    // key, value 방식으로 관리
    val numberMap = mapOf<String, Int>("one" to 1, "two" to 2)
    println(numberMap)
    println(numberMap.get("one"))


    // Mutable Collections
    println("Mutable Collections")
    val mNumberList = mutableListOf<Int>(1, 2, 3)
    mNumberList.add(3, 4)
    println(mNumberList)

    val mNumberSet = mutableSetOf<Int>(1, 2, 3, 4, 4, 4)
    mNumberSet.add(5)
    println(mNumberSet)

    val mNumberMap = mutableMapOf<String, Int>("one" to 1)
    mNumberMap.put("two", 2)
    println(mNumberMap)
}