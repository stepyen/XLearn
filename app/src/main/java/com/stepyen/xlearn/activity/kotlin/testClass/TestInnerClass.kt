package com.stepyen.xlearn.activity.kotlin.testClass

/**
 * date：2020-01-17
 * author：stepyen
 * description：测试-内部类
 *
 */
open class Foo {
    open fun f() {
        println("Foo.f()")
    }

    open val x: Int get() = 1
}

class Bar : Foo() {
    override fun f() {
        println("Bar.f()")
    }

    override val x: Int get() = 0

    /*内部类*/
    inner class BarInner {
        fun g() {
            println("BarInner.f()")

            super@Bar.f()
            f()

            println(super@Bar.x)


        }
    }
}
