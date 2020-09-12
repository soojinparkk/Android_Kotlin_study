package com.example.myapplication.Kotlin

// 평소 코딩할 때 private 사용,
// var 보다는 val 사용 추천

fun main(args:Array<String>) {
    val night = Night(20, 4)
    val monster = Monster(15, 5)

    night.attack(monster)
    monster.attack(night)
    night.attack(monster)
}

class Night(private var hp: Int, private var power: Int) {
    fun attack(monster: Monster) {
        monster.defense(power)
    }
    fun defense(damage: Int) {
        hp -= damage
        if (hp > 0) {
            heal()
            println("Night hp = $hp")
        }
        else
            println("Night died")
    }
    private fun heal() {
        hp += 3
    }
}

class Monster(private var hp: Int, private var power: Int) {
    fun attack(night: Night) {
        night.defense(power)
    }
    fun defense(damage: Int) {
        hp -= damage
        if (hp > 0)
            println("Monster hp = $hp")
        else
            println("Monster died")
    }
}