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
@file:Suppress("UNUSED")

package team.cakeslayers.xiguajerry.utils.command

fun commandsOf(vararg commands: AbstractCommand): CommandManager {
    val manager = CommandManager()
    commands.forEach { manager.add(it) }
    return manager
}

class CommandManager {
    private val commands = mutableListOf<AbstractCommand>()

    @Throws(SameCommandException::class)
    fun add(command: AbstractCommand) {
        if (commands.any { command.name.lowercase() == it.name.lowercase() }) {
            throw SameCommandException()
        } else {
            commands.add(command)
        }
    }

    @Throws(NoSuchCommandException::class, IllegalCommandArgumentsException::class)
    fun callCommand(commandHeader: String, vararg args: String) {
        val commandObject =
            commands.find { it.name.lowercase() == commandHeader.lowercase() } ?: throw NoSuchCommandException()
        commandObject.onCall(*args)
    }

    @Throws(NoSuchCommandException::class, IllegalCommandArgumentsException::class)
    fun callCommand(command: String) {
        val commandHeader = command.split(" ")[0]
        val args = command.split(" ").toMutableList()
        args.removeFirst()
        callCommand(commandHeader, *args.toTypedArray())
    }
}