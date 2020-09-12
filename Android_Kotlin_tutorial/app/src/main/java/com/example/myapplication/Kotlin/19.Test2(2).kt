package com.example.myapplication.Kotlin

// 2) 은행 계좌 생성
//  - 계좌 생성 가능 (이름, 생년월일)
//  - 잔고를 확인하는 기능
//  - 출금 기능
//  - 예금 기능

class Account(name: String, birth: String) {
    var money: Int = 0

    constructor(name: String, birth: String, money: Int): this(name, birth) {
        if (money >= 0)
            this.money = money
        else
            this.money = 0
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
        if (m > 0) {
            this.money += m
            println("${m}원 입금")
        }
        else
            println("입금 불가")
    }
}

// val, var 정하지 않는 변수를 생성자 안에 선언 가능 / 사용 가능
class Account2(initialBalance: Int) {
    val balance: Int = if (initialBalance > 0) initialBalance else 0

    fun check () {
        println("현재 잔액: ${balance}")
    }
}


fun main(args:Array<String>) {
    val second = Account("Suzan", "987654")
    second.check()
    second.withdraw(1000)
    second.deposit(15000)
    second.check()
    second.withdraw(3000)
    second.check()

    println()

    val second2 = Account2(5000)
    second2.check()
}