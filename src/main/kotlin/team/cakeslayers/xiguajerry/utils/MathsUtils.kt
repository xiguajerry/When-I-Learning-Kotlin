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
@file:JvmName("MathsUtils")
@file:Suppress("UNUSED", "unused_parameter")

package team.cakeslayers.xiguajerry.utils

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow
import kotlin.math.roundToInt

fun normalizeAngle(angleIn: Double) = when {
    angleIn % 360.0 >= 180.0 -> angleIn - 360.0
    angleIn % 360.0 < -180.0 -> angleIn + 360.0
    else -> angleIn
}

fun roundDouble(number: Double, scale: Int): Double {
    var bd = BigDecimal(number)
    bd = bd.setScale(scale, RoundingMode.HALF_UP)
    return bd.toDouble()
}

fun roundAvoid(value: Double, places: Int): Double {
    val scale = 10.0.pow(places.toDouble())
    return (value * scale).roundToInt() / scale
}

fun roundUp(value: Double) = (value + (1.0 - value)).toInt()

fun clamp(float: Float, min: Float, max: Float) = when {
    float <= min -> min
    float >= max -> max
    else -> float
}