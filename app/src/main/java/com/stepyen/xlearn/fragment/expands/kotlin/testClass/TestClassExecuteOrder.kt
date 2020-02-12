package com.stepyen.xlearn.fragment.expands.kotlin.testClass

/**
 * date：2020-01-17
 * author：stepyen
 * description：测试-类执行顺序
 *
 */
class TestClassExecuteOrder(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

    constructor(name: String, age: Int) : this(name) {
        print("constructor")
    }

}