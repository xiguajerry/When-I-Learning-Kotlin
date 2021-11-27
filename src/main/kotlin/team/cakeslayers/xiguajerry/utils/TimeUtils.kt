/*
 * Copyright (c) 2021-2021 CakeSlayers Reversing Team. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * CakeSlayers' Github website: https://github.com/CakeSlayers
 * This file was created by SagiriXiguajerry at 2021/10/15 下午10:55
 */
@file:JvmName("TimeUtils")
@file:Suppress("UNUSED", "DEPRECATION", "unused_parameter")

package team.cakeslayers.xiguajerry.utils

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

/**
 * return a time stamp
 * formatted like [hh:mm:ss]
 */
fun getTime(): String {
    val df = DecimalFormat("00")
    val date = Date(System.currentTimeMillis())
    val hours = df.format(date.hours)
    val minutes = df.format(date.minutes)
    val seconds = df.format(date.seconds)
    return "[$hours:$minutes:$seconds]"
}

private val formatterMap = HashMap<Pair<TimeFormat, TimeUnit>, DateTimeFormatter>()

fun getDate(): String = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))

fun getDate(dateFormat: DateFormat): String = LocalDate.now().format(dateFormat.formatter)

fun getSimpleTime(): String = LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))

fun getFormattedTime(timeFormat: TimeFormat, timeUnit: TimeUnit): String =
    LocalTime.now().format(timeFormat.getFormatter(timeUnit))

fun TimeFormat.getFormatter(timeUnit: TimeUnit) =
    formatterMap.getOrPut(this to timeUnit) {
        val pattern = if (timeUnit == TimeUnit.H24) pattern.replace('h', 'H') else "$pattern a"
        DateTimeFormatter.ofPattern(pattern, Locale.US)
    }

enum class DateFormat(val formatter: DateTimeFormatter) {
    DDMMYY(DateTimeFormatter.ofPattern("dd/MM/yy")),
    YYMMDD(DateTimeFormatter.ofPattern("yy/MM/dd")),
    MMDDYY(DateTimeFormatter.ofPattern("MM/dd/yy"))
}

enum class TimeFormat(val pattern: String) {
    HHMMSS("hh:mm:ss"),
    HHMM("hh:mm"),
    HH("hh")
}

enum class TimeUnit {
    H12, H24
}