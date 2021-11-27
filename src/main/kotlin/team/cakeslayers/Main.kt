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

@file:JvmName("Main")
@file:Suppress("UNUSED", "unused_parameter")

package team.cakeslayers

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import team.cakeslayers.xiguajerry.utils.animations.EaseUtils
import team.cakeslayers.xiguajerry.utils.debugging.DebugWindow
import javax.swing.SwingUtilities
import kotlin.concurrent.thread

/**
 * The entry where programmes start to run
 * @param args The arguments from caller
 */

fun main(args: Array<String>) {
//    EaseUtils.fromTo(0.0, 100.0) {
//        println(it)
//    }
    var point = Pair(0, 0)
    SwingUtilities.invokeLater {
        val window = DebugWindow("Test")
        thread {
            runBlocking {
                launch {
                    EaseUtils.fromToWithCoroutine(0, 1500, 6) {
//            window.setSize(it, 100)
                        window.setLocation((window.x + it + 900) / 5, (window.y + it) / 5)
                    }
                }
                launch {
                    EaseUtils.fromToWithCoroutine(window.width, 700, 10) {
                        window.setSize(it, it / 2)
                    }
                }
//                launch {
//                    EaseUtils.fromToWithCoroutine(
//                        window.x,
//                        Toolkit.getDefaultToolkit().screenSize.width + window.width
//                    ) {
//                        window.setLocation(it / 4, window.y)
//                    }
//                }
            }
        }
        window.isVisible = true
    }
}

