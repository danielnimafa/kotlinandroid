package com.loserba.android.utils.ext

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.EditText

/**
 * Created by danielnimafa on 04/04/18.
 */

val View.ctx: Context
    get() = context

fun View.click(klik: (View) -> Unit) {
    this.setOnClickListener { v -> klik(v) }
}

fun View.longClick(longKlik: (View) -> Unit) {
    this.setOnLongClickListener { v ->
        longKlik(v)
        true
    }
}

fun DialogFragment.getDisplayDimension(): Point {
    val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay

    val displayMetrics = DisplayMetrics()
    display.getMetrics(displayMetrics)
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels

    /*// find out if status bar has already been subtracted from screenHeight
    display.getRealMetrics( metrics );
    int physicalHeight = metrics.heightPixels;
    int statusBarHeight = getStatusBarHeight( context );
    int navigationBarHeight = getNavigationBarHeight( context );
    int heightDelta = physicalHeight - screenHeight;
    if ( heightDelta == 0 || heightDelta == navigationBarHeight )
    {
        screenHeight -= statusBarHeight;
    }*/

    return Point(screenWidth, screenHeight)
}

fun Activity.getNavBarHeight(): Int {
    var result = 0
    val resourceId = this.getResources()
            .getIdentifier("navigation_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = this.getResources().getDimensionPixelSize(resourceId)
    }
    return result
}

fun EditText.listenChanges(onTextChanged: (String) -> Unit, afterTextChanged: (String) -> Unit,
                           beforeTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChanged.invoke(s.toString())
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(s.toString())
        }

    })
}

fun EditText.afterTextChanged(teksBerubah: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            teksBerubah.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    })
}

fun EditText.onTextChanged(teksBerubah: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            teksBerubah.invoke(s.toString())
        }

    })
}

fun Activity.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = this.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Activity.dpToPxBasedOnDensity(valueDp: Int): Int {
    val scale = this.getResources().displayMetrics.density
    return (valueDp * scale + 0.5f).toInt()
}

fun Activity.dpToPx(dp: Int): Int =
        Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics))

fun Activity.dpToPx(valueInDp: Float): Float {
    val metrics = this.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics)
}