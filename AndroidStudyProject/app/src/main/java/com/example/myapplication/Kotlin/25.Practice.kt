package com.example.myapplication.Kotlin

// Night, Monster (부모)
// SuperNight, SuperMonster (자식)

// 상속이 만들어낸 특징
// - 자식 클래스는 부모 클래스의 타입임
// - 부모 클래스는 자식 클래스의 타입이 아님!!

fun main (args:Array<String>) {
    val night = SuperNight(100, 10)
    val monster = SuperMonster(100, 8)

    monster.attack(night)
    monster.bite(night)
}

open class Character(var hp: Int, val power: Int) {
    fun attack(character: Character, power: Int = this.power) {
        character.defense(power)
    }
    open fun defense(damage: Int) {
        hp -= damage
        if (hp > 0) {
            println("${javaClass.simpleName} hp = $hp")
        }
        else
            println("${javaClass.simpleName} died")
    }
}

// 자식 클래스가 인스턴스화 되기 위해서는
// 부모 클래스가 먼저 선언되어 인스턴스화 되어야 함
class SuperNight(hp: Int, power: Int): Character(hp, power) {
    val defensePower = 2
    override fun defense(damage: Int) {
        super.defense(damage - defensePower)
    }
}

class SuperMonster(hp: Int, power: Int): Character(hp, power) {
    fun bite(character: Character) {
        super.attack(character, power + 2)
    }
}