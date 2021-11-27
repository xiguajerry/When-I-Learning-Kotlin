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
@file:JvmName("RandomUtils")
@file:Suppress("UNUSED", "unused_parameter")

package team.cakeslayers.xiguajerry.utils

import java.util.*

inline fun invokeRandomly(randomSize: IntRange, luckyNumber: Int = randomSize.first, block: () -> Unit) {
    if (randomSize.random() == luckyNumber) block()
}

fun nextInt(startInclusive: Int, endExclusive: Int): Int {
    return if (endExclusive - startInclusive <= 0) startInclusive else startInclusive + Random().nextInt(endExclusive - startInclusive)
}

fun nextDouble(startInclusive: Double, endInclusive: Double): Double {
    return if (startInclusive == endInclusive || endInclusive - startInclusive <= 0.0) startInclusive else startInclusive + (endInclusive - startInclusive) * Math.random()
}

fun nextFloat(startInclusive: Float, endInclusive: Float): Float {
    return if (startInclusive == endInclusive || endInclusive - startInclusive <= 0f) startInclusive else (startInclusive + (endInclusive - startInclusive) * Math.random()).toFloat()
}

fun randomNumber(length: Int): String {
    return random(length, "123456789")
}

fun randomString(length: Int): String {
    return random(length, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz")
}

fun random(length: Int, chars: String): String {
    return random(length, chars.toCharArray())
}

fun random(length: Int, chars: CharArray): String {
    val stringBuilder = StringBuilder()
    for (i in 0 until length) stringBuilder.append(chars[Random().nextInt(chars.size)])
    return stringBuilder.toString()
}