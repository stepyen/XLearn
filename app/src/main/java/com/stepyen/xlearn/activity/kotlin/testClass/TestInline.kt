package com.stepyen.xlearn.activity.kotlin.testClass

/**
 * date：2020-03-27
 * author：stepyen
 * description：
 *
 */


inline fun onlyIf(check: Boolean, noinline action:()->Unit) {
    if (check) {
        action()
        check(action)
    }
}


fun check(body: () -> Unit) {

}


inline fun test(crossinline action: () -> Unit) {
    val a = object :Runnable{
        override fun run() {
            action()
        }
    }
}

