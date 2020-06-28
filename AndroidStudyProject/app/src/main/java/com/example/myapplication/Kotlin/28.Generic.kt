package com.example.myapplication.Kotlin

// Generic
// = 타입을 체크하는 기능
// - 목적: 다양한 타입의 객체들을 다루는 메서드나 컬렉션
//          클래스에서 컴파일시에 타입을 체크해주는 기능

// - 제너릭은 실제로 만들일이 거의 없기 때문에
//      사용하는 방법만 숙지하면 됨

fun main(args:Array<String>) {
    // 제너릭을 사용하지 않은 경우
    // 형 변환 필요 (타입 변환)
    val list1 = listOf(1, 2, 3, "a")
    val a: String = list1[2].toString()
    
    // 제너릭을 사용하는 경우
    // 타입이 안전함
    val list2 = listOf<String>("a", "b", "c")
    val b: String = list2[2]

    // 강한 타입 체크 가능 (타입 바로 알 수 있음)
    // 부모: Any
    // 자식: String, Int, Float
    val list3 = listOf<Any>(1, 2, 3, "a", "b", 4.0) // -> Any 타입
    val list4 = listOf<Int>(1, 2, 3, 4)
}
