package com.stepyen.xlearn.activity.kotlin.testClass

/**
 * date：2020-03-25
 * author：stepyen
 * description：测试关键字
 *
 * ?.
 * !!
 * ?:
 *
 */
class TestKeyWord {



    fun test1(msg: String?) {
        println("测试 ?: 开始测试")
        msg?:return
        println("测试 ?: 结束测试 msg:$msg")
    }


    fun test2() {

    }


}

fun main(args: Array<String>) {
    TestKeyWord().apply {
        test1(null)
        test1("haha")
    }
}

