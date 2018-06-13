package com.danielnimafa.android.androidkotlinapp.utils

import com.pixplicity.easyprefs.library.Prefs

object PrefHelper {
    fun loadBaseURL(): String = Prefs.getString(Const.ROOT_URL, "")
    fun saveBaseURL(url: String) {
        Prefs.putString(Const.ROOT_URL, url)
        Sout.log("saved new URL", loadBaseURL())
    }

    fun getDevMode(): Int = Prefs.getInt("mode", 0)
    fun setDevMode(mode: Int) {
        Prefs.putInt("mode", mode)
    }

    fun loadAppName(): String = Prefs.getString("appname", "")
    fun saveAppName(name: String) {
        Prefs.putString("appname", name)
        Sout.log("App Name", loadAppName())
    }
}