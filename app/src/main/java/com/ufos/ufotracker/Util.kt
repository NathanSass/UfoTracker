package com.ufos.ufotracker

import java.util.Calendar

object Util {
    private val months = "January February March April May June July August September October November December".split(" ")

    fun getDateString(timestamp: String): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp.toLong()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val hour = calendar.get(Calendar.HOUR) + 1
        val minute = calendar.get(Calendar.MINUTE) // BUG here: minutes here can return 1 digit. ex. 6 vs 06. We always want two digits
        val isAM = calendar.get(Calendar.AM_PM) == Calendar.AM
        val am = if (isAM) "AM" else "PM"
        val monthName = months[month % 12]
        return "$monthName $day, $year @ $hour:$minute $am"
    }
}