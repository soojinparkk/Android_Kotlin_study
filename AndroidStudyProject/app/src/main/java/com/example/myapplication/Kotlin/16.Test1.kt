package com.example.myapplication.Kotlin

// 1번 문제
// List 2개 생성
// 첫번째 list는 0-9까지 값을 넣음 (반복문 사용)
// 두번째 list는 첫번째 list 값을 하나씩 확인한 후
// 홀수면 false, 짝수면 true 넣어줌

fun listFun() {
    var a:Int = 0
    var firstList = mutableListOf<Int>()
    // val firstList2 = MutableList(10, { 0 })
    for (i in 0..9) {
        firstList.add(i)
        // firstList[i] = i
    }
    println(firstList)

    var secondList = mutableListOf<Boolean>()
    // val secondList2 = MutableList(9, { true })
    for (i in firstList) {
        if (firstList.get(i) % 2 == 0)
            secondList.add(true)
        else
            secondList.add(false)
    }
    println(secondList)

    /*firstList.forEachIndexed { index, b ->
        if (b % 2 == 0) secondList2[index] = true
        else secondList2[index] = false
    }*/
}

// 2번 문제
// 학점을 구하자
// 90 - 100 -> A
// 80 - 89 -> B
// 70 - 79 -> C
// 60 - 69 -> D
// 나머지 F

fun scoreFun(s:Int):String {
    when (s) {
        in 90..100 -> return "A"
        in 80..89 -> return "B"
        in 70..79 -> return "C"
        in 60..69 -> return "D"
        else -> return "F"
    }
}

// 3번 문제
// 전달 받은 숫자의 각 자리 수의 합을 구하자
// 조건: 전달 받은 숫자는 무조건 두자리 숫자

fun sumFun (num: Int): Int {
    val s: Int = num / 10
    val s2: Int = num % 10
    return s + s2
}

// 4번 문제
// 구구단 출력

fun multiplicationTable() {
    for (i in 1..9) {
        for (j in 1..9) {
            println("$i * $j = "+ i*j)
        }
    }
}

fun main(args:Array<String>) {
    listFun()
    println(scoreFun(80))
    println("각 자리 수의 합: " + sumFun(54))
    multiplicationTable()
}