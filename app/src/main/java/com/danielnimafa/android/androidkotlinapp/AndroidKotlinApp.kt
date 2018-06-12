package com.danielnimafa.android.androidkotlinapp

import android.content.Context
import android.content.ContextWrapper
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.danielnimafa.android.androidkotlinapp.utils.realmdb.DBMigration
import com.loserba.android.utils.ext.DelegatesExt
import com.pixplicity.easyprefs.library.Prefs
import io.realm.Realm
import io.realm.RealmConfiguration

class AndroidKotlinApp : MultiDexApplication() {

    companion object {
        var instance: AndroidKotlinApp by DelegatesExt.notNullSingleValue()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("androidkotlin.realm")
                .schemaVersion(0)
                .migration(DBMigration())
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)

        Prefs.Builder()
                .setContext(instance)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}