package com.M3.Messages.extensions

import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import java.util.Locale

fun Temporal.format(pattern: String): String {
    return DateTimeFormatter
        .ofPattern(pattern, Locale.getDefault())
        .format(this)
}
