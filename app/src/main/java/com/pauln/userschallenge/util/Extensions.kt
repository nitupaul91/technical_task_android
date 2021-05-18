package com.pauln.userschallenge.util

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

val emailAddressPattern: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"
)

fun String.relativeTimeToNow(): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("GMT")

    sdf.parse(this)?.let {
        val now = System.currentTimeMillis()
        return DateUtils.getRelativeTimeSpanString(it.time, now, DateUtils.MINUTE_IN_MILLIS)
            .toString()
    }
    return null
}

fun isEmailInvalid(email: String?): Boolean {
    return email.isNullOrEmpty() ||
        !emailAddressPattern.matcher(email).matches()
}
