package com.example.userlist.common

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {
    fun extractHourMinuteFromDate(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val dateTime = LocalDateTime.parse(dateString, formatter)

        val hourMinuteFormatter = DateTimeFormatter.ofPattern("h:mm")

        return dateTime.format(hourMinuteFormatter)
    }
}