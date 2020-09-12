package com.example.myapplication.Kotlin


fun main(args:Array<String>) {

    val testAccess: TestAccess = TestAccess("su")
    // private 때문에 외부에서 해당 함수 사용 불가
    //testAccess.test()
    println(testAccess.name)

    val reward: Reward = Reward()
    // private 때문에 외부에서 더이상 사용 불가
    //reward.rewardAmount = 2000
}

class TestAccess {
    var name: String = "suzan"

    constructor(name: String){
        this.name = name
    }

    private fun test() {
        println("test")
    }
}

class Reward() {
    // private -> 이 클래스 외부에서 값 임의로 변경 불가
    private var rewardAmount: Int = 1000
}