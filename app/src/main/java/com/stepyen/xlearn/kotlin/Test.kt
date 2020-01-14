package com.stepyen.xlearn.kotlin

/**
 * date：2020-01-14
 * author：stepyen
 * description：
 *
 */
class Test {


}

/**
 * main方法创建方式2
 */
fun main(args: Array<String>) {

    val a: Int = 999
    val b: Int? = a
    val c: Int? = a
    println(b == c)    //true
    println(b === c)   //false
}



