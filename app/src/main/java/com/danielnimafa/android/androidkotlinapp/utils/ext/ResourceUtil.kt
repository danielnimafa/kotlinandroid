package com.danielnimafa.android.androidkotlinapp.utils.ext

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import com.danielnimafa.android.androidkotlinapp.utils.Sout
import java.util.*

/**
 * Created by danielnimafa on 04/04/18.
 */

fun Activity.drawableGet(res: Int): Drawable = ContextCompat.getDrawable(this, res)!!

fun Activity.colorGet(res: Int): Int = ContextCompat.getColor(this, res)

fun Activity.stringGet(res: Int): String = resources.getString(res)

fun updateColor(color: String, view: View): Drawable {
    val gd: GradientDrawable = view.background as GradientDrawable
    gd.setColor(Color.parseColor(color))
    return gd
}

fun updateResource(context: Context, language: String): Resources {
    var resources = context.resources
    val dm = resources.displayMetrics
    val conf = resources.configuration
    /*Locale sysLocale = null;
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    sysLocale = conf.getLocales().get(0);
} else {
    sysLocale = conf.locale;
}*/

    val locale = Locale(language)
    Locale.setDefault(locale)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        conf.setLocale(locale)
    } else {
        conf.locale = locale
    }

    var konteks: Context? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        konteks = context.createConfigurationContext(conf)
        Sout.log("versi", "more than jelly bean")
        resources = konteks!!.resources
    } else {
        resources.updateConfiguration(conf, dm)
    }

    return resources
}