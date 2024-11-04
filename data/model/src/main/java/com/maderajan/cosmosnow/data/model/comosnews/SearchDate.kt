package com.maderajan.cosmosnow.data.model.comosnews

import com.maderajan.cosmosnow.data.model.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

enum class SearchDate {
    TODAY, LAST_WEEK, LAST_MOTH;

    fun getPresentableName(): Int =
        when (this) {
            TODAY -> R.string.date_today
            LAST_WEEK -> R.string.date_last_week
            LAST_MOTH -> R.string.date_last_month
        }
}

fun SearchDate?.getFromToDateString(): Pair<String?, String?> =
    when (this) {
        SearchDate.TODAY -> {
            val from = Calendar.getInstance().startOfDay()
            from.timeInMillis.toApiTimeFormat() to Calendar.getInstance().timeInMillis.toApiTimeFormat()
        }

        SearchDate.LAST_WEEK -> {
            val from = Calendar.getInstance().startOfDay()
            from.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            from.add(Calendar.DAY_OF_MONTH, -7)

            val to: Calendar = (from.clone() as Calendar).endOfDay()
            to.add(Calendar.DAY_OF_MONTH, 6)

            from.timeInMillis.toApiTimeFormat() to to.timeInMillis.toApiTimeFormat()
        }

        SearchDate.LAST_MOTH -> {
            val from = Calendar.getInstance().startOfDay()
            from.add(Calendar.DAY_OF_MONTH, -30)
            from.set(Calendar.DAY_OF_MONTH, 1)

            val to: Calendar = (from.clone() as Calendar).endOfDay()
            to.set(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH))

            from.timeInMillis.toApiTimeFormat() to to.timeInMillis.toApiTimeFormat()
        }

        else -> null to null
    }

fun Long.toApiTimeFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
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