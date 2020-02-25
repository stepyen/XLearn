package com.stepyen.xlearn.activity.kotlin.testClass

/**
 * date：2020-01-17
 * author：stepyen
 * description：
 *
 *
 *
 *
 */
open class Class3 {
    open val x: Int = 5

    open var y = 6

    open val z = 7

    constructor(){
        println("Class3.constructor()")
    }

    open fun test1() {
        println("Class3.test1()")
    }

    fun test2() {
        println("Class3.test2()")
    }

    open fun test3() {
        println("Class3.test3()")
    }
}

open class Class4 : Class3 {

    override val x: Int = 5

//    override val y = 6        // 不可以， var变成val是不可以的，能力可以扩大，不能变小。

    override var z = 7

    constructor( name: String) : super() {
        println("Class4.constructor()")
    }

    override fun test1() {
        println("Class4.test1()")
    }

//    fun test2() {         // 报错，子类不能使用和父类相同签名的方法，除非父类open
////
////    }

    final override fun test3() {    // final 不希望子类再继承
        println("Class4.test3()")
    }
}













