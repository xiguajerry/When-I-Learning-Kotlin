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

package team.cakeslayers.xiguajerry.utils.thread

class TimeCount(callBack: (Int, Int, Int) -> Unit) {
    private var minutes = 0
    private var hours = 7
    private var days = 0

    private var thread = Thread {
        while (true) {
            Thread.sleep(1000)
            minutes++
            if (minutes >= 60) {
                minutes = 0
                hours++
            }
            if (hours >= 24) {
                hours = 0
                days++
            }
            callBack(minutes, hours, days)
        }
    }

    fun start() {
        thread.start()
    }

    fun setTime(min: Int, h: Int, d: Int) {
        require(min >= 60 || h >= 24 || d >= 365) { "Illegal time arguments." }
        minutes = min
        hours = h
        days = d
    }
}