package com.example.myapplication.Kotlin

// 3) TV 클래스
//  - on/off 기능
//  - 채널을 돌리는 기능
//  - 초기 채널은 (S, M, K 3개)

class TV (val channels: List<String>) {
    var onOrOff: Boolean = false
    var currentChannelNumber = 0
        set(value) {
            // 값이 할당될 때 무한루프 도는 것을 방지 = field
            field = value
            if (field > 2)
                field = 0
            if (field < 0)
                field = 2
        }
        get() {
            println("호출")
            return field
        }
    // currentChannelNumber에 값이 할당될 때 set 실행
    // 반드시 변수명 밑에 set을 선언해야 함
    // currentChannelNumber를 호출할 때마다 get 실행

    init {
        println("초기 채널 S, M, K")
    }

    fun switch () {
        onOrOff = !onOrOff
        if (onOrOff)
            println("TV ON")
        else
            println("TV OFF")
    }

    fun checkCurrentChannel (): String {
        return channels[currentChannelNumber]
    }

    fun channelUp () {
        currentChannelNumber += 1

        //channels.forEachIndexed { index, value ->
        //    if (currentChannelNumber == index) {
        //        currentChannelNumber += 1
        //        return
        //    }
        //}
    }

    fun channelDown () {
       currentChannelNumber -= 1
    }
}

fun main(args:Array<String>) {
    val channels = listOf<String>("S", "M", "K")
    val third = TV(channels)
    third.switch()
    println(third.checkCurrentChannel())
    third.channelUp()
    third.channelUp()
    third.channelUp()
    third.channelUp()
    third.channelDown()
    println(third.checkCurrentChannel())
    third.switch()
}