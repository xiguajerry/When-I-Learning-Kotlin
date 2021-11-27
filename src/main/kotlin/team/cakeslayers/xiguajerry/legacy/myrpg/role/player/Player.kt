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

package team.cakeslayers.xiguajerry.legacy.myrpg.role.player

import team.cakeslayers.xiguajerry.legacy.myrpg.item.AbstractItem
import team.cakeslayers.xiguajerry.legacy.myrpg.item.Hitable
import team.cakeslayers.xiguajerry.legacy.myrpg.item.UsingResult
import team.cakeslayers.xiguajerry.legacy.myrpg.map.AbstractArea
import team.cakeslayers.xiguajerry.legacy.myrpg.map.areas.Home
import team.cakeslayers.xiguajerry.legacy.myrpg.role.AbstractHuman
import team.cakeslayers.xiguajerry.legacy.myrpg.role.Fightable

class Player(name: String, home: Home) : AbstractHuman(name, "A player") {
    private var inventory: MutableList<AbstractItem?> = MutableList(50) { null }
    private var location: AbstractArea

    init {
        location = home
    }

    fun getAura(): String = "\n>>> Player Info <<<\n" +
            "(Name:$name) (HP:$healthPoints) (Aura:${
                when (healthPoints) {
                    in 80..100 -> "GREEN"
                    in 40..80 -> "ORANGE"
                    else -> "RED"
                }
            })\n${
                when (healthPoints) {
                    100 -> "$name is in excellent condition!"
                    in 90..99 -> "$name has a few scratches."
                    in 75..89 -> "$name has some minor wounds."
                    in 20..74 -> "$name looks pretty hurt."
                    else -> "$name IS IN AWFUL CONDITION!"
                }
            }"

    fun enterArea(area: AbstractArea) {
        if (area == location) {
            println("You are already in this area.")
            return
        }
        println("You leave ${location.name}.")
        location = area
        println("CurrentLocation: ${location.name}\nDescription: $location\n")
        area.onPlayerEnter()
    }

    fun getLocation() = location

    override fun attack(opponent: Fightable, weapon: Hitable) {
        opponent.healthPoints -= weapon.damage
        println("You used ${weapon.name} to take the opponent ${weapon.damage} HP!")
        opponent.beAttacked(weapon.damage)
    }

    override fun beAttacked(damage: Int) {
        healthPoints -= damage
        if (healthPoints <= 0) println("You are dead!")
    }

    fun useItem(item: AbstractItem): UsingResult {
        return if (item in inventory) {
            inventory -= item
            UsingResult.SUCCESS
        } else {
            UsingResult.FAILED
        }
    }
}