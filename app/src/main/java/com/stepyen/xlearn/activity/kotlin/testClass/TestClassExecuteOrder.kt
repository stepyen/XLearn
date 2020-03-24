package com.stepyen.xlearn.activity.kotlin.testClass

/**
 * date：2020-01-17
 * author：stepyen
 * description：测试-类执行顺序
 *
 */
class TestClassExecuteOrder(name: String) {                         // 0
    val firstProperty = "First property: $name".also(::println)     // 1

    init {
        println("First initializer block that prints ${name}")      // 2
    }

    val secondProperty = "Second property: ${name.length}".also(::println)  //3

    init {
        println("Second initializer block that prints ${name.length}")//4
    }

    constructor(name: String, age: Int) : this(name) {      // -1
        println("constructor")                          // 5
    }

}