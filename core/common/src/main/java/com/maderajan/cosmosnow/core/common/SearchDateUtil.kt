package com.maderajan.cosmosnow.core.common

import com.maderajan.cosmosnow.data.model.comosnews.SearchDate
import java.util.Calendar

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