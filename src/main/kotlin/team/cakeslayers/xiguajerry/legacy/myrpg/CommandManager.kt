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

package team.cakeslayers.xiguajerry.legacy.myrpg

import team.cakeslayers.xiguajerry.legacy.myrpg.map.NoSuchAreaException
import team.cakeslayers.xiguajerry.utils.command.AbstractCommand
import team.cakeslayers.xiguajerry.utils.command.CommandManager
import team.cakeslayers.xiguajerry.utils.command.IllegalCommandArgumentsException
import team.cakeslayers.xiguajerry.utils.command.NoSuchCommandException
import kotlin.system.exitProcess

object CommandManager {
    private val commandManager = CommandManager()

    private val enter = object : AbstractCommand("enter") {
        override fun onCall(vararg commandArgs: String) {
            if (commandArgs.size != 1) throw IllegalCommandArgumentsException("You've sent illegal arg(s)!")
            try {
                MyRPG.player.enterArea(AreasManager.getAreaByName(commandArgs[0]))
            } catch (e: NoSuchAreaException) {
                println("There is no area called ${commandArgs[0]}")
            }
        }
    }

    private val exit = object : AbstractCommand("exit") {
        override fun onCall(vararg commandArgs: String) {
            exitProcess(0)
        }
    }

    init {
        commandManager.add(enter)
        commandManager.add(exit)
    }

    @JvmStatic
    @Throws(NoSuchCommandException::class, IllegalCommandArgumentsException::class)
    fun callCommand(command: String) = commandManager.callCommand(command)
}