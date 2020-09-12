package com.example.myapplication.Kotlin

// 1) 사칙 연산 수행하는 클래스

class Operations() {
    fun plus(num1: Int, num2: Int) {
        println("$num1 + $num2 = ${num1 + num2}")
    }
    fun minus(num1: Int, num2: Int) {
        println("$num1 - $num2 = ${num1 - num2}")
    }
    fun multiply(num1: Int, num2: Int) {
        println("$num1 * $num2 = ${num1 * num2}")
    }
    fun divide(num1: Int, num2: Int) {
        println("$num1 / $num2 = ${num1 / num2}")
    }
}

class Operations2() {
    fun plus(vararg numbers: Int) {
        var result = 0
        numbers.forEach {
            result += it
        }
        println("plus result= $result")
    }
    fun minus(vararg numbers: Int) {
        var result = numbers[0]
        for (i in 1 until numbers.size)
            result = result - numbers[i]
        println("minus result= $result")
    }
    fun multiply(vararg numbers: Int) {
        var result = 1
        numbers.forEach {
            if (it != 0)
                result *= it
        }
        println("multiply result= $result")
    }
    fun divide(vararg numbers: Int) {
        var result = numbers[0]
        numbers.forEachIndexed { index, i ->
            if (index != 0 && i != 0)
                result /= i
        }
        println("divide result= $result")
    }
}

class Operations3(val initialValue: Int) {
    fun plus(num: Int): Operations3 {
        val result = this.initialValue + num
        return Operations3(result)
    }
    fun minus(num: Int): Operations3 {
        val result = this.initialValue - num
        return Operations3(result)
    }
    fun multiply(num: Int): Operations3 {
        val result = this.initialValue * num
        return Operations3(result)
    }
    fun devide(num: Int): Operations3 {
        val result = this.initialValue / num
        return Operations3(result)
    }

}

fun main(args: Array<String>){
    val first = Operations()
    first.plus(30, 5)
    first.minus(30, 5)
    first.multiply(30, 5)
    first.divide(30, 5)

    println()

    val first2 = Operations2()
    first2.minus(10, 1, 2, 3)

    println()

    val first3 = Operations3(10)
    first3.plus(2).minus(5).multiply(2)
    // -> Chaining (체이닝)
}