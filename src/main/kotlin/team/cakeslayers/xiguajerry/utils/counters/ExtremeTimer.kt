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

open class ExtremeTimer {
    var time = currentTime; protected set

    protected val currentTime get() = System.currentTimeMillis()

    fun reset(offset: Long = 0L) {
        time = currentTime + offset
    }

}

class TickExtremeTimer(val timeUnit: TimeUnit = TimeUnit.MILLISECONDS) : ExtremeTimer() {
    fun tick(delay: Int, resetIfTick: Boolean = true): Boolean {
        return tick(delay.toLong(), resetIfTick)
    }

    fun tick(delay: Long, resetIfTick: Boolean = true): Boolean {
        return if (currentTime - time > delay * timeUnit.multiplier) {
            if (resetIfTick) time = currentTime
            true
        } else {
            false
        }
    }
}

class StopExtremeTimer(val timeUnit: TimeUnit = TimeUnit.MILLISECONDS) : ExtremeTimer() {
    fun stop(): Long {
        return (currentTime - time) / timeUnit.multiplier
    }
}

enum class TimeUnit(val multiplier: Long) {
    MILLISECONDS(1L),
    TICKS(50L),
    SECONDS(1000L),
    MINUTES(60000L);
}