package com.maderajan.cosmosnow.core.common

import org.junit.Test
import java.util.Calendar

class DateFormatUtilTest {

    @Test
    fun isMillisDate_Sat2Nov2024InMillis_FormatedToReadable() {
        val dateMillis = 1730532082137L
        assert("Sat, 2 Nov 2024" == dateMillis.toWeekdayMonthReadableDate())
    }

    @Test
    fun isMillisDate_Mon27Apr2015InMillis_FormatedToReadable() {
        val dateMillis = 1430125082157L
        assert("Mon, 27 Apr 2015" == dateMillis.toWeekdayMonthReadableDate())
    }

    @Test
    fun isApiDateFormat_20241101_FormatedCorrectly() {
        val dateString = "2024-11-01T22:34:26Z"
        assert("01. 11. 2024" == dateString.dayMonthYearReadableDate())
    }

    @Test
    fun isApiDateFormat_20230705_FormatedCorrectly() {
        val dateString = "2023-07-05T22:34:26Z"
        assert("05. 07. 2023" == dateString.dayMonthYearReadableDate())
    }

    @Test
    fun isDate_1530125082157_FormatedToApiDateFormat() {
        val dateMillis = 1530125082157L
        assert("2018-06-27T20:44:42.157Z" == dateMillis.toApiTimeFormat())
    }

    @Test
    fun isDate_1730125082157_FormatedToApiDateFormat() {
        val dateMillis = 1730125082157L
        assert("2024-10-28T15:18:02.157Z" == dateMillis.toApiTimeFormat())
    }

    @Test
    fun isCalendar_SetOnStartOfTheDay() {
        val startTheDay = Calendar.getInstance().apply {
            set(2000, 10, 10, 10, 10)
        }.startOfDay()

        assert(
            startTheDay.get(Calendar.DAY_OF_MONTH) == 10 &&
            startTheDay.get(Calendar.MONTH) == 10 &&
            startTheDay.get(Calendar.YEAR) == 2000 &&
            startTheDay.get(Calendar.HOUR_OF_DAY) == 0 &&
            startTheDay.get(Calendar.MINUTE) == 0 &&
            startTheDay.get(Calendar.SECOND) == 0 &&
            startTheDay.get(Calendar.MILLISECOND) == 0
        )
    }

    @Test
    fun isCalendar_SetOnEndOfTheDay() {
        val startTheDay = Calendar.getInstance().apply {
            set(2000, 10, 10, 10, 10)
        }.endOfDay()

        assert(
            startTheDay.get(Calendar.DAY_OF_MONTH) == 10 &&
                    startTheDay.get(Calendar.MONTH) == 10 &&
                    startTheDay.get(Calendar.YEAR) == 2000 &&
                    startTheDay.get(Calendar.HOUR_OF_DAY) == 23 &&
                    startTheDay.get(Calendar.MINUTE) == 59 &&
                    startTheDay.get(Calendar.SECOND) == 59 &&
                    startTheDay.get(Calendar.MILLISECOND) == 999
        )
    }
}