package com.example.myapplication.Kotlin

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
fun main(args:Array<String>) {

    val a = mutableListOf<Int>(1, 2, 3)
    a.add(4)
    println(a)
    a.add(0, 100)   // add -> 해당 인덱스에 값 추가
    println(a)
    a.set(0, 200)   // set -> 해당 인덱스 값을 바꿈
    println(a)
    a.removeAt(1)   // removeAt -> 해당 인덱스 값 삭제
    println(a)

    val b = mutableSetOf<Int>(1, 2, 3, 4)
    b.add(2)
    println(b)
    b.remove(2) // 없는 값을 삭제하려 해도 error 발생하지 않음
    println(b)

    val c = mutableMapOf<String, Int>("one" to 1)
    c.put("two", 2)
    println(c)
    c.replace("two", 3) // replace -> 해당하는 key 값의 value 값 변경
    println(c)
    println(c.keys)
    println(c.values)
    c.clear()   // clear -> Map 값 전체 삭제
    println(c)

}