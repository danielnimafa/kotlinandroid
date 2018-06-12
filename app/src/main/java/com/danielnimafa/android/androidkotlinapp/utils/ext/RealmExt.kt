package com.izzyparcel.android.courier.utils.ext

import android.app.Activity
import android.content.Intent
import com.izzyparcel.android.courier.model.realm.Profil
import com.izzyparcel.android.courier.utils.Sout
import com.izzyparcel.android.courier.view.impl.auth.LoginActivity
import io.realm.Realm

fun deleteUserdataAndLogout(realm: Realm, activity: Activity) {

    realm.executeTransactionAsync(
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
            })
}