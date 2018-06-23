package com.danielnimafa.android.androidkotlinapp.utils

/**
 * Created by danielnimafa on 02/27/18.
 *
 * This is for helping me to log everything into string format
 * I can simply change the dev mode into 0 for production code
 *
 */

object Sout {
    fun log(key: String, any: Any?) {
        if (PrefHelper.getDevMode() == 1) println(key + ": " + any)
    }

    fun trace(e: Exception) {
        if (PrefHelper.getDevMode() == 1) e.printStackTrace()
    }

    fun thisContext(activityClass: Class<*>) {
        log("View Context", activityClass.simpleName)
    }
}