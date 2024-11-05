package com.maderajan.cosmosnow.core.common

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Long.toWeekdayMonthReadableDate(): String {
    val dateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
    return dateFormat.format(this)
}

fun String.dayMonthYearReadableDate(): String {
    val apiDate = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(this)
    val dateFormat = SimpleDateFormat("dd. mm. yyyy", Locale.getDefault())
    return dateFormat.format(apiDate)
}

fun Long.toApiTimeFormat(locale: Locale = Locale.getDefault()): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", locale)
    return format.format(this)
}

fun Calendar.startOfDay(): Calendar =
    this.apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

fun Calendar.endOfDay(): Calendar =
    this.apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }