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

@file:Suppress("UNUSED")

package team.cakeslayers.xiguajerry.utils.counters

import java.time.Duration
import java.time.Instant

class FullTimer {
    var time: Long
    fun resetTimeSkipTo(ms: Int) {
        time = System.currentTimeMillis() + ms
    }

    fun passed(ms: Double): Boolean {
        return System.currentTimeMillis() - time >= ms
    }

    fun passedSec(second: Double): Boolean {
        return System.currentTimeMillis() - time >= second * 1000
    }

    fun passedTick(tick: Double): Boolean {
        return System.currentTimeMillis() - time >= tick * 50
    }

    fun passedMin(minutes: Double): Boolean {
        return System.currentTimeMillis() - time >= minutes * 1000 * 60
    }

    fun reset(): FullTimer {
        time = System.currentTimeMillis()
        return this
    }

    fun hasPassed(): Long {
        return System.currentTimeMillis() - time
    }

    companion object {
        fun now(): Instant {
            return Instant.now()
        }

        fun getSecondsPassed(start: Instant?, current: Instant?): Long {
            return Duration.between(start, current).seconds
        }

        fun haveSecondsPassed(start: Instant?, current: Instant?, seconds: Long): Boolean {
            return getSecondsPassed(start, current) >= seconds
        }

        fun getMilliSecondsPassed(start: Instant?, current: Instant?): Long {
            return Duration.between(start, current).toMillis()
        }

        fun haveMilliSecondsPassed(start: Instant?, current: Instant?, milliseconds: Long): Boolean {
            return getMilliSecondsPassed(start, current) >= milliseconds
        }
    }

    init {
        time = -1
    }
}