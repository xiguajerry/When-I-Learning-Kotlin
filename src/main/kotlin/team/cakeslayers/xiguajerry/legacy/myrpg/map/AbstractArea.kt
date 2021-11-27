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
@file:Suppress("UNUSED", "DEPRECATION")

package team.cakeslayers.xiguajerry.legacy.myrpg.map

abstract class AbstractArea(_name: String, _welcometext: String = "Welcome to $_name!", _population: Long = 0) {
    open val name: String = _name
    open val welcometext: String = _welcometext
    open val population: Long = _population

    open fun onPlayerEnter() {
        println(welcometext)
    }

    override fun toString() = "An area called $name,has a population of $population"
}