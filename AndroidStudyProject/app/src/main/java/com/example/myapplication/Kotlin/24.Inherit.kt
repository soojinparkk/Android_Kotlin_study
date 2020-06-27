package com.example.myapplication.Kotlin

// 두번까지는 봐준다
// 두번 이상이 넘어가면 리펙토링 해라

// 상속
// = 부모로부터 설명서를 물려받음

fun main (args:Array<String>) {
    val superCar1: SuperCar1 = SuperCar1()
    println(superCar1.drive())
    superCar1.stop()
}

// class 기본으로 private 설정되어 있기 때문에
// open class 설정해야 함
open class Car1() {
    open fun drive(): String {
        return "drive"
    }
    fun stop() {
        println("stop")
    }
}

class SuperCar1(): Car1() {
    override fun drive(): String {
        val run = super.drive()
        return "super $run"
    }
}

class Bus1(): Car1() {

}