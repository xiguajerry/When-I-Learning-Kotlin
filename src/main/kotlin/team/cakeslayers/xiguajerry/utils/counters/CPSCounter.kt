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

class RollingArrayLongBuffer(length: Int) {
    private var contents = mutableListOf<Long>()
    private var currentIndex = 0

    init {
        contents = MutableList(length) { 0L }
    }

    /**
     * @return The contents of the buffer
     */
    fun getContents(): MutableList<Long> {
        return contents
    }

    /**
     * Adds an element to the buffer
     *
     * @param l The element to be added
     */
    fun add(l: Long) {
        currentIndex = (currentIndex + 1) % contents.size
        contents[currentIndex] = l
    }

    /**
     * Gets the count of elements added in a row
     * which are higher than l
     *
     * @param l The threshold timestamp
     * @return The count
     */
    fun getTimestampsSince(l: Long): Int {
        for (i in contents.indices) {
            if (contents[if (currentIndex < i) contents.size - i + currentIndex else currentIndex - i] < l) {
                return i
            }
        }

        // If every element is lower than l, return the array length
        return contents.size
    }
}

class CPSCounter {
    private val MAX_CPS = 50
    private val TIMESTAMP_BUFFERS = MutableList(MouseButton.values().size) { RollingArrayLongBuffer(0) }

    init {
        for (i in 0..TIMESTAMP_BUFFERS.size) {
            TIMESTAMP_BUFFERS[i] = RollingArrayLongBuffer(MAX_CPS)
        }
    }

    /**
     * Registers a mouse button click
     *
     * @param button The clicked button
     */
    fun registerClick(button: MouseButton) {
        TIMESTAMP_BUFFERS[button.index].add(System.currentTimeMillis())
    }

    /**
     * Gets the count of clicks that have occurrence since the last 1000ms
     *
     * @param button The mouse button
     * @return The CPS
     */
    fun getCPS(button: MouseButton): Int {
        return TIMESTAMP_BUFFERS[button.index].getTimestampsSince(System.currentTimeMillis() - 1000L)
    }

    enum class MouseButton(val index: Int) {
        LEFT(0), MIDDLE(1), RIGHT(2);

    }
}