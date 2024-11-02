package com.maderajan.cosmosnow.core.designsystem

import com.maderajan.cosmosnow.core.designsystem.util.dayMonthYearReadableDate
import com.maderajan.cosmosnow.core.designsystem.util.toWeekdayMonthReadableDate
import org.junit.Test

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
}