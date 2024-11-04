package com.maderajan.cosmosnow.data.model.comosnews

import com.maderajan.cosmosnow.data.model.R

enum class DateSelect {
    TODAY, LAST_WEEK, LAST_MOTH;

    fun getPresentableName(): Int =
        when (this) {
            TODAY -> R.string.date_today
            LAST_WEEK -> R.string.date_last_week
            LAST_MOTH -> R.string.date_last_month
        }
}