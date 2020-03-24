package com.stepyen.xlearn.activity.kotlin.testClass

/**
 * date：2020-03-24
 * author：stepyen
 * description：测试空指针相关问题
 *
 */
class TestNull {


    fun testJudgeNull() {
        println("-----------------------testJudgeNull 开始--------------------------")


        var user1: User?= null
        val name1 = user1?.car?.name?.let { "车的名字：$it" }?:"为空"

        println("测试为空的情况：$name1")


        var user2: User?= User().apply {
            car = Car().apply {
                name = "很nice的法拉利"
            }
        }
        val name2 = user2?.car?.name?.let { "他的名字：$it" }?:"为空"

        println("测试不为空的情况：$name2")

        println("-----------------------testJudgeNull 结束--------------------------")

    }


    inner class User {
        var car: Car? = null
    }

    inner class Car {
        var name: String? = null

    }


}