package com.example.myapplication.Kotlin

// OOP -> Object Oriented Programming (객체지향 프로그래밍)
// 객체: 이름이 있는 모든 것

// 절차지향 프로그래밍
// - 코드를 위에서 부터 아래로 실행하여 문제를 해결
// 객제지향 프로그래밍
// - 객체를 만들어서, 객체에게 일을 시켜서 문제를 해결

// 객체 생성 방법
// -> 프레임워크가 제공해주지 않는 것들 생성
// - 설명서 필요 (클래스 == 설명서)

// 클래스 생성 방법(1)
class Car (var engine: String, var body: String) {

}

// 클래스 생성 방법(2)
class SuperCar {
    var engine: String = ""
    var body: String = ""
    var door: String = ""
    constructor(engine: String, body: String, door: String) {
        this.engine = engine
        this.body = body
        this.door = door
    }
}

// 클래스 생성 방법(3) -> (1) 방법의 확장
class MyCar(engine: String, body: String) {
    var door: String = ""

    // 생성자 == constructor
    constructor(engine: String, body: String, door: String): this(engine, body) {
        this.door = door
    }
}

// 클래스 생성 방법(4) -> (2) 방법의 확장
class MyCar2 {
    var engine: String = ""
    var body: String = ""
    var door: String = ""

    constructor(engine: String, body: String) {
        this.engine = engine
        this.body = body
    }

    constructor(engine: String, body: String, door: String) {
            this.engine = engine
            this.body = body
            this.door = door
    }
}


// RunableCar == RunableCar2 같은 클래스
class RunableCar(engine: String, body: String) {
    fun ride() {
        println("ride")
    }

    fun drive() {
        println("drive")
    }

    fun nave(dest: String) {
        println("$dest(으)로 destination 설정")
    }
}

class RunableCar2 {
    var engine: String
    var body: String

    constructor(engine: String, body: String) {
        this.engine = engine
        this.body = body
    }

    // 인스턴스화 될 때 가장 먼저 실행 (객체 생성 시)
    // 초기 세팅을 할 때 사용 ex) 변수 초기화
    init {
        println("RunableCar2 생성 완료")
    }

    fun ride() {
        println("ride")
    }

    fun drive() {
        println("drive")
    }

    fun nave(dest: String) {
        println("$dest(으)로 destination 설정")
    }
}

// 오버로딩
// - 이름이 같지만 받는 파라미터가 다른 함수
class TestClass() {
    fun test(a: Int) {
        println("Up")
    }

    fun test(a: Int, b: Int) {
        println("Down")
    }
}


fun main (args:Array<String>) {

    // 클래스를 통해서 실체를 만드는 방법
    // -> 인스턴스화 -> 인스턴스 == 객체
    Car("v8", "big")
    // 만든 클래스는 자료형이 됨
    val bigCar: Car = Car("v8", "big")

    val bigSuperCar: SuperCar = SuperCar("good", "big", "white")

    val runableCar: RunableCar = RunableCar("simple", "small")
    runableCar.ride()
    runableCar.drive()
    runableCar.nave("Seoul")


    // 인스턴스의 멤버 변수에 접근하는 방법
    val runableCar2: RunableCar2 = RunableCar2("nice engine", "big body")
    println(runableCar2.engine)
    println(runableCar2.body)
    runableCar2.drive()


    // 오버로딩
    val testClass = TestClass()
    testClass.test(1)
    testClass.test(1, 2)
}