package com.stepyen.xlearn.fragment.expands.kotlin.testClass

/**
 * date：2020-01-17
 * author：stepyen
 * description：测试-继承参数
 *
 */
open class Base(val name: String) {

    init {
        println("Base-init")
    }

    open val size: Int = name.length.also { println("init size in Base: $it") }
}


class Derived(
        name: String,
        val lastName: String
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {

    init {
        println("Derived-init")
    }

    override val size: Int =
            (super.size + lastName.length).also { println("init size in Derived: $it") }

}