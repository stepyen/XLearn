package com.stepyen.xlearn.activity.kotlin

import com.stepyen.xlearn.activity.kotlin.testClass.*
import kotlin.properties.Delegates

/**
 * date：2020-01-14
 * author：stepyen
 * description：
 *
 *
 *
 * 委托属性
 * http://www.kotlincn.net/docs/reference/delegated-properties.html#%E5%B1%9E%E6%80%A7%E5%A7%94%E6%89%98%E8%A6%81%E6%B1%82
 *
 */
class Test {


}

/**
 * main方法创建方式2
 */
fun main(args: Array<String>) {
//    TestNull().apply {
//        testJudgeNull()
//    }





//    testNull()
//    testEqual()
//    testRequire()
//    testCheck()

//    testDelegates()
//    testDelegatesObservable()
//    testDelegatesMap()
//
//    testExtend1()
//    testExtend2()
//    testClassExecuteOrder()
//    testTestExtendPara()

    TestLambda().apply {
        testAction = {
            println(it)
        }

        test()
    }



}


fun testInLine() {

    onlyIf(false) {
        println("执行action方法")
    }



}



fun testNull() {
    println("-----------------------testNull 开始--------------------------")

    val s1 :String?=null
    val s2 :String = ""
    val s3 :String = "123"

    val print1 :String= s1?.takeIf { it.isNotEmpty() }?: run {
        "s1 is null"
    }

    val print2 :String= s2?.takeIf { it.isNotEmpty() }?: run {
        "s2 is null"
    }

    val print3 :String= s3?.takeIf { it.isNotEmpty() }?: run {
        "s3 is null"
    }
    println(print1)   // "s1 is null"
    println(print2)   // "s2 is null"
    println(print3)   // "123"






    println("-----------------------testNull 结束--------------------------")

}

fun testEqual() {

    println("-----------------------testEqual 开始--------------------------")

    println("// 数值自动装箱比较")
    val a: Int = 999    // 数值
    val b: Int? = a     // ? 表示不为空，b会自动装箱
    val c: Int? = a     // ? 表示不为空，c会自动装箱
    println(b == c)    //true
    println(b === c)   //false


    println("// 对象比较")
    var user1 = User("xiaoming", 18)
    var user2 = User(age = 18)

    println("equals1:${user1 == user2 } ")// 值比较，true
    println("equals2:${user1 === user2 }")// 地址比较，false


    println("// 对象比较,equal 重新了主构造函数中的参数")
    var user3 = User("xiaoming", 18)
    user3.year =1998
    var user4 = User("xiaoming", 18)
    user4.year =2000

    println("equals3:${user3 ==user4} ")// 值比较，true，因为equal方法只重新了主构造函数中的参数
    println("equals4:${user3 === user4}")// 地址比较，false


    println("-----------------------testEqual 结束--------------------------")
}


/**
 * require 用于检查 参数
 *
 * 抛出异常 IllegalArgumentException
 */
fun testRequire() {
    println("-----------------------testRequire 开始--------------------------")
    println("// 如果 name 为空，抛出异常 IllegalArgumentException")
    val name = ""
    require(name.isNotEmpty()) { "invalid name" }
    val name2 = "name2"
    require(name2.isNotEmpty()) { "invalid name2" }

    println("-----------------------testRequire 结束--------------------------")
}

/**
 * check 检查参数状态 抛出IllegalStateException
 */
fun testCheck() {
    println("-----------------------testCheck 开始--------------------------")
    println("// 如果 name 为空，抛出异常 IllegalStateException")
    val name = ""
    check(name.isNotEmpty()){"invalid name"}
    val name2 = "name2"
    check(name2.isNotEmpty()){"invalid name"}

    println("-----------------------testCheck 结束--------------------------")
}





/**
 * 委托模式
 */
fun testDelegates() {
    println("-----------------------testDelegates 开始--------------------------")

    // 委托模式 是 继承的替代方式。
    val b = BaseDelegateImpl(10)
    var baseDelegateDrive = BaseDelegateDrive(b)
    baseDelegateDrive.print()

    println("-----------------------testDelegates 结束--------------------------")
}


fun testDelegatesObservable() {
    println("----------------------- 开始--------------------------")

    val user = DelegatesObservableUser()
    println("// Delegates.observable")
    user.name = "xiaoming"
    println(user.name)
    user.name = "xiaoming2"
    println(user.name)

    println("// Delegates.vetoable")
    user.age = 30
    println(user.age)
    user.age = 40
    println(user.age)

    println("----------------------- 结束--------------------------")
}

fun testDelegatesMap() {
    println("-----------------------testDelegatesMap 开始--------------------------")

    // 字段名要和UserMap中写的字段名一致才可以
    val userMap = DelegatesUserMap(
            mapOf(
                    "name" to "John Doe",
                    "age" to 25
            )
    )
    println(userMap.name)
    println(userMap.age)


    println("-----------------------testDelegatesMap 结束--------------------------")

}






class DelegatesObservableUser {
    // observable("no name") 默认值O
    // oldValue 旧值
    // newValue 新值
    var name: String by Delegates.observable("no name") { property, oldValue, newValue ->
        println("property : $property， oldValue ：$oldValue ，newValue : $newValue")
    }

    var age: Int by Delegates.vetoable(18) { property, oldValue, newValue ->
        println("property : $property， oldValue ：$oldValue ，newValue : $newValue")
        var result = false
        if (newValue >= 30) {
            result = true
        }
        result
    }
}

/**
 * 只读map
 */
class DelegatesUserMap(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

/**
 * 可读写map
 */
class DelegatesMutableUserMap(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int     by map
}


interface BaseDelegate{
    fun print()
}

class BaseDelegateImpl(val  x:Int): BaseDelegate {
    override fun print() {
        println("BaseDelegateImpl print")
    }
}

class BaseDelegateDrive(val b: BaseDelegate): BaseDelegate by b{

    override fun print() {
        b.print()
        println("BaseDelegateDrive print")
    }
}


/**
 * 测试-继承
 */
fun testExtend1() {
    println("----------------------- 开始--------------------------")

    println( C().f())

    println("----------------------- 结束--------------------------")
}

/**
 * 测试-继承2
 */
fun testExtend2() {
    println("----------------------- 开始--------------------------")

    Class4("name").test1()


    println("----------------------- 结束--------------------------")
}


/**
 * 测试-类执行顺序
 */
fun testClassExecuteOrder() {
    println("------------------类执行顺序----- 开始--------------------------")

    TestClassExecuteOrder("姓名",123)

    println("----------------------- 结束--------------------------")

}

/**
 * 测试-集成参数
 */
fun testTestExtendPara() {

    println("-----------------------testTestExtendPara 开始--------------------------")

    Derived("name", "lastName")

    println("-----------------------testTestExtendPara 结束--------------------------")

}



fun test() {
    println("----------------------- 开始--------------------------")


    println("----------------------- 结束--------------------------")
}

