package com.danielnimafa.android.androidkotlinapp.utils.ext

import android.app.Activity
import android.content.Intent
import com.danielnimafa.android.androidkotlinapp.utils.Sout
import com.danielnimafa.android.androidkotlinapp.view.LoginActivity
import io.realm.Realm

/*
 * Created by danielnimafa on 02/27/18.
 */

fun deleteUserdataAndLogout(realm: Realm, activity: Activity) {

    /*realm.executeTransactionAsync(
            {
                it.where(Profil::class.java).findAll().apply { deleteAllFromRealm() }
            },
            {
                activity.run {
                    startActivity(LoginActivity[this].apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                    finish()
                }
                val users = realm.where(Profil::class.java).findAll()
                Sout.log("Users size", users.size)
            },
            {
                Sout.trace(it as Exception)
            })*/
}