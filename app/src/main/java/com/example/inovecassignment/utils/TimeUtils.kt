package com.example.inovecassignment.utils

import com.example.inovecassignment.constants.WEEKDAYS_FORMAT
import java.text.SimpleDateFormat
import java.util.*


val  currentDateTime: Date = Calendar.getInstance().time

fun Date.toString(format: String = WEEKDAYS_FORMAT, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}
