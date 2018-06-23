package com.danielnimafa.android.androidkotlinapp.utils.ext

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.danielnimafa.android.androidkotlinapp.R
import com.loserba.android.utils.ext.stringGet
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun showAlertMessage(activity: Activity, title: String? = "Title", message: String? = "Pesan") {
    val ad = AlertDialog.Builder(activity).create()
    ad.setTitle(title)
    ad.setMessage(message)
    ad.setCanceledOnTouchOutside(false)
    ad.setCancelable(false)
    ad.setButton(DialogInterface.BUTTON_POSITIVE, "ok", { dialog, which ->
        dialog.dismiss()
    })
    ad.show()
}

fun Activity.showPopupMessage(title: String? = "Title", message: String? = "Pesan") {
    val ad = AlertDialog.Builder(this).create()
    ad.setTitle(title)
    ad.setMessage(message)
    ad.setCanceledOnTouchOutside(false)
    ad.setCancelable(false)
    ad.setButton(DialogInterface.BUTTON_POSITIVE, "ok", { dialog, which ->
        dialog.dismiss()
    })
    ad.show()
}

fun Activity.showAlertActionMessage(message: String = "Pesan", title: String? = "Info",
                                    positiveAction: (DialogInterface, Int) -> Unit,
                                    negativeAction: (DialogInterface, Int) -> Unit,
                                    positiveLabel: String? = "Ok", negativeLabel: String? = "Batal") {
    val ad = AlertDialog.Builder(this).create()
    ad.setTitle(title)
    ad.setMessage(message)
    ad.setCanceledOnTouchOutside(false)
    ad.setCancelable(false)
    ad.setButton(DialogInterface.BUTTON_NEGATIVE, negativeLabel, { dialog, which ->
        negativeAction(dialog, which)
    })
    ad.setButton(DialogInterface.BUTTON_POSITIVE, positiveLabel, { dialog, which ->
        positiveAction(dialog, which)
    })
    ad.show()
}

fun Activity.showAlertActionMessageUnit(activity: Activity, message: String = "Pesan", title: String? = "Info",
                                        onPositive: () -> Unit, onNegative: () -> Unit,
                                        positiveLabel: String? = "Ok", negativeLabel: String? = "Batal"
) {
    val ad = AlertDialog.Builder(activity).create()
    ad.setTitle(title)
    ad.setMessage(message)
    ad.setCanceledOnTouchOutside(false)
    ad.setCancelable(false)
    ad.setButton(DialogInterface.BUTTON_NEGATIVE, negativeLabel, { dialog, which ->
        dialog.dismiss()
        onNegative()
    })
    ad.setButton(DialogInterface.BUTTON_POSITIVE, positiveLabel, { dialog, which ->
        dialog.dismiss()
        onPositive()
    })
    ad.show()
}

fun showAlertSingleActionMessage(activity: Activity, message: String = "Pesan", title: String? = "Info",
                                 callbackAction: (DialogInterface, Int) -> Unit,
                                 positiveLabel: String? = "Ok"
) {
    val ad = AlertDialog.Builder(activity).create()
    ad.setTitle(title)
    ad.setMessage(message)
    ad.setCanceledOnTouchOutside(false)
    ad.setCancelable(false)
    ad.setButton(DialogInterface.BUTTON_POSITIVE, positiveLabel, { dialog, which ->
        callbackAction(dialog, which)
    })
    ad.show()
}

interface DialogSingleActionListener {
    fun action(dialogInterface: DialogInterface, which: Int)
}

fun networkError(e: Throwable, activity: Activity) {
    var res: Int = 0
    if (e is SocketTimeoutException) {
        res = R.string.message_timeout
    } else if (e is SocketException || e is UnknownHostException) {
        res = R.string.message_server_lost_conn
    } else if (e is NullPointerException) {
        res = R.string.message_invalid_response
    }

    var msg = if (res != 0) activity.getString(res) else e.message!!
    showAlertMessage(activity, "Info", msg)
}