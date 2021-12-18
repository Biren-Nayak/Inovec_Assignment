package com.example.inovecassignment.utils

import com.example.inovecassignment.constants.DATE_FORMAT
import com.example.inovecassignment.constants.WEEkDAYS_FORMAT
import java.text.SimpleDateFormat
import java.util.*


val  currentDateTime: Date = Calendar.getInstance().time

fun Date.toString(format: String = WEEkDAYS_FORMAT, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}
