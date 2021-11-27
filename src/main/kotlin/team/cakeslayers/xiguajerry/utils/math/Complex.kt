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
 * This file was created by SagiriXiguajerry at 2021/11/5 下午7:57
 */

package team.cakeslayers.xiguajerry.utils.math

class Complex(var re: Double = 0.0, var im: Double = 0.0) {
    operator fun plus(other: Double) {
        this.re += other
    }

    operator fun plus(other: Float) {
        this.re += other
    }

    operator fun plus(other: Int) {
        this.re += other
    }

    operator fun plus(other: Long) {
        this.re += other
    }

    operator fun plus(other: Byte) {
        this.re += other
    }

    operator fun plus(other: Short) {
        this.re += other
    }

    operator fun plus(other: UShort) {
        this.re += other.toInt()
    }

    operator fun plus(other: UInt) {
        this.re += other.toInt()
    }

    operator fun plus(other: ULong) {
        this.re += other.toInt()
    }

    operator fun plus(other: Complex) {
        this.re += other.re
        this.im += other.im
    }

    operator fun minus(other: Double) {
        this.re -= other
    }

    operator fun minus(other: Float) {
        this.re -= other
    }

    operator fun minus(other: Int) {
        this.re -= other
    }

    operator fun minus(other: Long) {
        this.re -= other
    }

    operator fun minus(other: Byte) {
        this.re -= other
    }

    operator fun minus(other: Short) {
        this.re -= other
    }

    operator fun minus(other: UShort) {
        this.re -= other.toInt()
    }

    operator fun minus(other: UInt) {
        this.re -= other.toInt()
    }

    operator fun minus(other: ULong) {
        this.re -= other.toInt()
    }

    operator fun minus(other: Complex) {
        this.re -= other.re
        this.im -= other.im
    }

    override fun toString(): String {
        if (re == 0.0 && im == 0.0) return "0"
        if (re == 0.0) return "${im}i"
        if (im == 0.0) return "im"
        return "$re+${im}i"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Complex

        if (re != other.re) return false
        if (im != other.im) return false

        return true
    }
}