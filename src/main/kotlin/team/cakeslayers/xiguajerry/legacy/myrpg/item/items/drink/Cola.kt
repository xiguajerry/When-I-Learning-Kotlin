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

package team.cakeslayers.xiguajerry.legacy.myrpg.item.items.drink

import team.cakeslayers.xiguajerry.legacy.myrpg.item.*
import team.cakeslayers.xiguajerry.legacy.myrpg.role.player.Player

class Cola(override val name: String, override val price: Double, override val ml: Int) : AbstractItem(), Useable,
    Sellable, Drinkable {
    override fun use(player: Player) {
        if (player.useItem(this) == UsingResult.SUCCESS) {
            println("Use $name successfully!")
        }
    }

    override fun getSellingDescription() = "Price: $price CNY, $ml mL"
}