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

package team.cakeslayers.xiguajerry.legacy.myrpg

import team.cakeslayers.xiguajerry.legacy.myrpg.item.items.drink.Cola
import team.cakeslayers.xiguajerry.legacy.myrpg.map.AbstractArea
import team.cakeslayers.xiguajerry.legacy.myrpg.map.NoSuchAreaException
import team.cakeslayers.xiguajerry.legacy.myrpg.map.areas.Home
import team.cakeslayers.xiguajerry.legacy.myrpg.map.areas.Shop
import team.cakeslayers.xiguajerry.legacy.myrpg.map.areas.Village

object AreasManager {
    @JvmField
    val simVillage = object : Village("SimVillage") {
        override fun onPlayerEnter() {
            super.onPlayerEnter()
            println("The villagers rally and cheer as you enter!")
            println("The Bell Tower announces your arrival.GWONG")
        }
    }

    @JvmField
    val drinkingShop = Shop(
        "SimShop",
        "Welcome to SimShop!",
        _commodities = mapOf(
            Cola("可口可乐-无糖", 3.5, 350) to 3.5,
            Cola("可口可乐", 3.5, 1000) to 3.5,
            Cola("可口可乐", 3.5, 2000) to 3.5,
            Cola("可口可乐", 3.5, 350) to 3.5,
        ),
    )

    @JvmField
    val home = Home("Home", "Welcome to your home!")

    private val areas = listOf(simVillage, drinkingShop, home)

    @Throws(NoSuchAreaException::class)
    fun getAreaByName(name: String): AbstractArea {
        if (areas.none { it.name.lowercase() == name.lowercase() }) {
            throw NoSuchAreaException()
        }
        return areas.find { it.name.lowercase() == name.lowercase() }!!
    }

}