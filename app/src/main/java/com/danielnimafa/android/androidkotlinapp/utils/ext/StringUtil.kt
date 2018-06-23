package com.danielnimafa.android.androidkotlinapp.utils.ext

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.experimental.and

/*
 * Created by danielnimafa on 02/27/18.
 */

fun String.encrypt(): String = Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)

fun String.decrypt(): String = String(Base64.decode(this, Base64.DEFAULT))

fun String.trimThis(data: String?): String {
    var result = ""
    if (data != null && data.trim().isNotEmpty()) {
        result = data
    } else {
        result = "-"
    }
    return result
}

fun makeDefaultThisValue(data: String?): String {
    var result = ""
    if (data != null && data.trim().isNotEmpty()) {
        result = data
    } else {
        result = "0"
    }
    return result
}

fun String.randomNumber(range: Int): String {
    val rn = Random()
    val random = rn.nextInt(range)
    return random.toString()
}

fun nullCheck(value: String?): String = value ?: "null"