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
 * This file was created by SagiriXiguajerry at 2021/11/5 上午1:24
 */

@file:JvmName("Loggers")
@file:Suppress("UNUSED", "unused_parameter")

package team.cakeslayers

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import team.cakeslayers.xiguajerry.utils.forNameOrNull

/**
 * Input a Class instance, and return a Logger
 * @param clazz The caller's [Class] instance
 * @return An instance of [org.apache.logging.log4j.Logger]
 * @author SagiriXiguajerry
 */
fun getLogger(clazz: Class<*>?): Logger {
    return LogManager.getLogger(clazz ?: UnknownClass::class.java)!!
}

val mainLogger: Logger = getLogger(forNameOrNull("team.cakeslayers.Main"))

val easeLogger: Logger = getLogger(forNameOrNull("team.cakeslayers.xiguajerry.utils.animations.EaseUtils"))

/**
 * Can be used when the param of the function [getLogger] is null
 * @author SagiriXiguajerry
 */
class UnknownClass