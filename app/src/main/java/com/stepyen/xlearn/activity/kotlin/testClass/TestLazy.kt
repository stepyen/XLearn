package com.stepyen.xlearn.activity.kotlin.testClass

/**
 * date：2020-04-01
 * author：stepyen
 * description：测试懒加载 Lazy
 *
 */
class TestLazy {

    private val book:Book by lazy{
        Book()
    }


}