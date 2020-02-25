package com.stepyen.xlearn.activity.kotlin.testClass

/**
 * date：2020-01-17
 * author：stepyen
 * description：测试继承
 *
 * 1、学习继承的两个类都有相同的可方继承方法，如何调用各自类的法
 *
 */
open class A {
    open fun f() {
        print("A.f()")
    }

    fun a() {
        print("A.a()")
    }
}

// 接口成员默认就是“open”的
interface B {
    fun f() {
        print("B.f()")
    }

    fun b() {
        print("B.b()")
    }
}

class C : A(), B {

    // 编译器要求覆盖 f()：
    override fun f() {
        super<A>.f() // 调用 A.f()
        super<B>.f() // 调用 B.f()
    }

}