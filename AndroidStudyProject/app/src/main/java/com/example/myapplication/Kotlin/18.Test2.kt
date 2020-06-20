package com.example.myapplication.Kotlin

// 1) 사칙 연산 수행하는 클래스

class Operations() {
    fun plus(num1: Int, num2: Int) {
        println("$num1 + $num2 = ${num1 + num2}")
    }
    fun substract(num1: Int, num2: Int) {
        println("$num1 - $num2 = ${num1 - num2}")
    }
    fun multiplication(num1: Int, num2: Int) {
        println("$num1 * $num2 = ${num1 * num2}")
    }
    fun division(num1: Int, num2: Int) {
        println("$num1 / $num2 = ${num1 / num2}")
    }
}

// 2) 은행 계좌 생성
//  - 계좌 생성 가능 (이름, 생년월일)
//  - 잔고를 확인하는 기능
//  - 출금 기능
//  - 예금 기능

class Account(name: String, birth: String) {
    var money: Int = 0

    constructor(name: String, birth: String, money: Int): this(name, birth) {
        this.money = money
    }

    fun check () {
        println("현재 잔액: ${this.money}")
    }

    fun withdraw(m: Int) {
        if (this.money <= 0 || this.money <= m)
            println("출금 불가")
        else {
            println("${m}원 출금")
            this.money -= m
        }
    }

    fun deposit(m: Int) {
        this.money += m
    }
}

// 3) TV 클래스
//  - on/off 기능
//  - 채널을 돌리는 기능
//  - 초기 채널은 (S, M, K 3개)

class Tv () {
    init {
        println("현재 채널: ")
    }

    fun on () {
        println("TV on")
    }

    fun off () {
        println("TV off")
    }

    fun channel () {

    }
}

fun main(args: Array<String>){
    val first = Operations()
    first.plus(30, 5)
    first.substract(30, 5)
    first.multiplication(30, 5)
    first.division(30, 5)

    val second = Account("Suzan", "987654")
    second.check()
    second.withdraw(1000)
    second.deposit(15000)
    second.check()
    second.withdraw(3000)
    second.check()

    val third = Tv()
}