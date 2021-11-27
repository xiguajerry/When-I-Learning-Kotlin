/*
 * Copyright (c) 2021 CakeSlayers Reversing Team. All Rights Reserved.
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
 * This file was created by SagiriXiguajerry at 2021/11/7 上午2:08
 */

package team.cakeslayers.xiguajerry.utils.animations

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import team.cakeslayers.easeLogger
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.math.abs

object EaseUtils {
    @JvmStatic
    suspend fun fromToWithCoroutine(start: Double, end: Double, rate: Double, callback: (Double) -> Unit) {
        var step: Double
        var currentPosition = start
        while (abs(currentPosition - end) >= 20) {
            val balance = end - currentPosition
            step = balance / rate * 0.5
            currentPosition += step
            callback(currentPosition)
            easeLogger.info(currentPosition)
            delay(10)
        }
        easeLogger.info("Eased successfully")

    }

    @JvmStatic
    suspend fun fromToWithCoroutine(start: Int, end: Int, rate: Int, callback: (Int) -> Unit) {
        var step: Int
        var currentPosition = start
        while (abs(currentPosition - end) >= 20) {
            coroutineScope {
                val balance = end - currentPosition
                step = balance / rate / 2
                currentPosition += step
                callback(currentPosition)
                easeLogger.info(currentPosition)
                delay(10)
            }
        }
        easeLogger.info("Eased successfully")
    }


    @JvmStatic
    fun fromTo(start: Int, end: Int, rate: Int, callback: (Int) -> Unit) {
        var step: Int
        var currentPosition = start
        thread {
            while (abs(currentPosition - end) >= 20) {
                val balance = end - currentPosition
                step = balance / rate / 2
                currentPosition += step
                callback(currentPosition)
                easeLogger.info(currentPosition)
                sleep(10)
            }
            easeLogger.info("Eased successfully")
        }
    }

    @JvmStatic
    fun fromTo(start: Double, end: Double, rate: Double, callback: (Double) -> Unit) {
        var step: Double
        var currentPosition = start
        thread {
            while (abs(currentPosition - end) >= 20) {
                val balance = end - currentPosition
                step = balance / rate * 0.5
                currentPosition += step
                callback(currentPosition)
                easeLogger.info(currentPosition)
                sleep(10)
            }
            easeLogger.info("Eased successfully")
        }
    }
}