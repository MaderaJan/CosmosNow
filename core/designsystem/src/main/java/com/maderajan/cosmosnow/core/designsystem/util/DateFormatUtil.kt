package com.maderajan.cosmosnow.core.designsystem.util

import java.text.SimpleDateFormat
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