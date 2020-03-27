package com.stepyen.xlearn.activity.kotlin.testClass

/**
 * date：2020-03-27
 * author：stepyen
 * description：
 *
 */
class TestLambda {

    var testAction:((String)->Unit) ?= null

    fun test() {

        testAction?.let {
            it("haha")
        }


    }

    val sum = { x: Int, y: Int -> x + y }

    val a = fun(x: Int, y: Int): Int = x + y

}