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

import team.cakeslayers.xiguajerry.legacy.myrpg.AreasManager.home
import team.cakeslayers.xiguajerry.legacy.myrpg.role.player.Player
import team.cakeslayers.xiguajerry.utils.command.IllegalCommandArgumentsException
import team.cakeslayers.xiguajerry.utils.command.NoSuchCommandException
import team.cakeslayers.xiguajerry.utils.println
import java.util.logging.Logger

object MyRPG {
    /**
     * This is a starting function with an endless loop
     *
     * YOU SHOULD USE IT AFTER ALL THE INITIALIZATION IS DONE!
     * @return Nothing
     */
    @JvmStatic
    fun start(): Nothing {
        while (true) {
            player.getAura().println()
            print(">Enter your command: ")
            val cmd = readLine()!!
            try {
                CommandManager.callCommand(cmd)
            } catch (e: NoSuchCommandException) {
                println("Unknown command(s)!")
            } catch (e: IllegalCommandArgumentsException) {
                println(e.message)
            }
        }
    }

    @JvmField
    val logger: Logger = Logger.getLogger("team.cakeslayers.xiguajerry.legacy.myrpg.Start")

    @JvmField
    val player = Player("SagiriXiguajerry", home)
}