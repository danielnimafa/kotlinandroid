package com.danielnimafa.android.androidkotlinapp.utils.ext

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Handler
import android.os.Vibrator
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.danielnimafa.android.androidkotlinapp.utils.Sout


/*
 * Created by danielnimafa on 02/27/18.
 */

fun Activity.getarChoy() {
    val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vib.vibrate(100)
}

fun Activity.playScanSound() {
    try {
        val afd = assets.openFd("audio/plucky.mp3")
        MediaPlayer().apply {
            setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            prepare()
            start()
        }
    } catch (e: Exception) {
        Sout.trace(e)
    }
}

fun Activity.hideSoftKeyboard(ed: EditText) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(ed.getWindowToken(), 0)
}

fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}

fun Activity.hideCurrentSoftKeyboard() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
}

fun Activity.verifyPermission(granResult: IntArray): Boolean {
    if (granResult.isEmpty()) {
        return false
    }

    granResult.filter { it != PackageManager.PERMISSION_GRANTED }.forEach {
        return false
    }

    return true
}

fun isThisDeviceSupportCamera(appContext: Context): Boolean =
        appContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)

fun postDelayed(todo: () -> Unit, delayMillis: Long) {
    Handler().postDelayed({ todo.invoke() }, delayMillis)
}