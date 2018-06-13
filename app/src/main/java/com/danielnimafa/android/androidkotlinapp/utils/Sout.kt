package com.danielnimafa.android.androidkotlinapp.utils

/**
 * Created by danielnimafa on 07/12/17.
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