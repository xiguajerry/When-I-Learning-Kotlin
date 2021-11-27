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

@file:Suppress("UNUSED", "DEPRECATION")

package team.cakeslayers.xiguajerry.utils.debugging

import team.cakeslayers.xiguajerry.utils.getFullStackTrace
import team.cakeslayers.xiguajerry.utils.getHalfStackTrace
import team.cakeslayers.xiguajerry.utils.getTime
import team.cakeslayers.xiguajerry.utils.println
import java.util.*
import kotlin.system.exitProcess

object LogUtils {
    fun debug(message: String, caller: String) {
        buildString {
            append(getTime())
            append(" [main/DEBUG] [$caller]: ")
            append(message)
        }.println()
    }

    fun info(message: String, caller: String) {
        buildString {
            append(getTime())
            append(" [main/INFO] [$caller]: ")
            append(message)
        }.println()
    }

    fun warn(message: String, caller: String) {
        buildString {
            append(getTime())
            append(" [main/WARN] [$caller]: ")
            append(message)
        }.println()
    }

    fun error(message: String = "Catching", caller: String, vararg throwable: Throwable) {
        buildString {
            append(getTime())
            append(" [main/ERROR] [$caller]: $message\n")
            append("${throwable[0].getHalfStackTrace()}\n")
            throwable.drop(1).forEach {
                append("${it.getFullStackTrace()}\n")
            }
        }.println()
    }

    fun fatal(message: String = "Unreported exception thrown!", caller: String, error: Error) {
        buildString {
            append(getTime())
            append(" [main/FATAL] [$caller]: $message\n")
            append(error.getHalfStackTrace())
            append(getTime())
            append(" [main/FATAL] [STDOUT]: ---Crash Report---\n\n")
            val date = Date(System.currentTimeMillis())
            append("Time: ${date.day}/${date.month}/${date.year} ${date.hours}:${date.minutes}\n")
            append("Description: Unexpected error\n\n")
            append(error.getHalfStackTrace())
            append("\n\nA detailed walk through of the error, its code path and all known details is as follows:\n")
            append("---------------------------------------------------------------------------------------\n\n")
            append("-- Head --\n")
            append("Stacktrace:\n")
            if (error.stackTrace.size >= 3) {
                (0..2).forEach {
                    append("\tat ${error.stackTrace[it].className}:${error.stackTrace[it].lineNumber}\n")
                }
            } else {
                (0 until error.stackTrace.size).forEach {
                    append("\tat ${error.stackTrace[it].className}:${error.stackTrace[it].lineNumber}\n")
                }
            }
            append(getTime())
            append(" [main/FATAL] [STDOUT]:  #@!@# Client Crashed! Exiting... #@!@#")
        }.println()
        exitProcess(-1)
    }
}