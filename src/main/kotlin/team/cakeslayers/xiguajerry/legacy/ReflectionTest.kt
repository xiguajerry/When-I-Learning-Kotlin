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

@file:JvmName("ReflectionTest")
@file:Suppress("UNUSED", "DEPRECATION")

package team.cakeslayers.xiguajerry.legacy

import team.cakeslayers.xiguajerry.legacy.testclasses.Student
import team.cakeslayers.xiguajerry.utils.println
import kotlin.system.measureNanoTime

fun invokeByReflection() {
    measureNanoTime {
        val clazz = Student::class.java
        val constructor = clazz.getConstructor()
        val obj = constructor.newInstance()
        val method = clazz.getMethod("doCalc", Int::class.java)
        repeat(100000) {
            method.invoke(obj, 1)
        }

    }.println()
}

fun invokeByNormal() {
    measureNanoTime {
        val student = Student()
        repeat(100000) {
            student.doCalc(1)
        }
    }.println()
}

fun invokePrivateMethod() {
    measureNanoTime {
        val clazz = Student::class.java
        val constructor = clazz.getConstructor()
        val obj = constructor.newInstance()
        val method = clazz.getMethod("doCalcPrivate", Int::class.java)
        method.isAccessible = true
        repeat(100000) {
            method.invoke(obj, 1).println()
        }
    }.println()
}