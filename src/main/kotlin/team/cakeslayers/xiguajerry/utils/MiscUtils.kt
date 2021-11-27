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

@file:Suppress("UNUSED", "DEPRECATION", "unused_parameter")
@file:JvmName("MiscUtils")

package team.cakeslayers.xiguajerry.utils

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import team.cakeslayers.xiguajerry.utils.counters.MSTimer
import java.awt.Desktop
import java.io.File
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.util.*
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JOptionPane

fun showErrorPopup(title: String, message: String) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE)
}

fun showURL(url: String) {
    try {
        Desktop.getDesktop().browse(URI(url))
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: URISyntaxException) {
        e.printStackTrace()
    }
}

fun openFileChooser(): File? {
    val fileChooser = JFileChooser()
    val frame = JFrame()
    fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY
    frame.isVisible = true
    frame.toFront()
    frame.isVisible = false
    val action = fileChooser.showOpenDialog(frame)
    frame.dispose()
    return if (action == JFileChooser.APPROVE_OPTION) fileChooser.selectedFile else null
}

fun saveFileChooser(): File? {
    val fileChooser = JFileChooser()
    val frame = JFrame()
    fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY
    frame.isVisible = true
    frame.toFront()
    frame.isVisible = false
    val action = fileChooser.showSaveDialog(frame)
    frame.dispose()
    return if (action == JFileChooser.APPROVE_OPTION) fileChooser.selectedFile else null
}

fun crashing(): String = crashing()

@Deprecated("Throws IllegalFormatConversionException", ReplaceWith("println()"))
fun printf(format: String, vararg args: Any) {
    System.out.printf(format, *args)
}

operator fun String.times(times: Int) = repeat(times)

inline infix operator fun String.div(other: String) {

}

fun getPrimers(count: Int) = generateSequence(3) { it + 1 }.filter { it.isPrimer() }.take(count).toList()

fun movingWindow() {
    val valuesToAdd = listOf(1, 18, 73, 3, 44, 6, 1, 33, 2, 22, 5, 7)
    val step1 = valuesToAdd.filter { it >= 5 }
    val step2 =
        step1.filterIndexed { index, _ -> index % 2 == 0 }.zip(step1.filterIndexed { index, _ -> index % 2 != 0 })
            .toMap()
    var num = 0
    step2.forEach { (num1, num2) -> num += num1 * num2 }
    val step3 = num
    valuesToAdd.println()
    step1.println()
    step2.println()
    step3.println()
}

fun P1116() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val a = MutableList(n) { scanner.nextInt() }
    var cnt = 0
    var hold: Int
    for (pass in 1 until n) {
        for (i in 0 until n - pass) {
            if (a[i] > a[i + 1]) {
                cnt++
                hold = a[i]
                a[i] = a[i + 1]
                a[i + 1] = hold
            }
        }
    }
    println(cnt)
}

fun P5660() {
    println(readLine()!!.count { it != '0' })
}

fun P5705() {
    println(readLine()!!.reversed())
}

fun P5710() {
    val n = Scanner(System.`in`).nextInt()
    print(
        (n % 2 == 0 && n in 5..12).toInt().toString() + " "
                + (n % 2 == 0 || n in 5..12).toInt().toString() + " "
                + (n % 2 == 0 && n !in 5..12 || n % 2 != 0 && n in 5..12).toInt().toString() + " "
                + (!(n % 2 == 0 || n in 5..12)).toInt().toString()
    )
}

fun P5715() {
    readLine()!!.split(" ").map { it.toInt() }.sortedWith(Comparator.comparingInt { it }).forEach { print("$it ") }
}

fun P1059() {
    readLine()
    val a = readLine()!!.split(" ").distinct().map { it.toInt() }.sortedWith(Comparator.comparingInt { it })
    print("${a.size}\n")
    a.forEach { print("$it ") }
}

fun P1427() {
    val a = readLine()!!.split(" ").toMutableList()
    a.removeLast()
    a.reversed().forEach { print("$it ") }
}

fun P5534() {
    val a = readLine()!!.split(" ").map { it.toInt() }
    val head = a[0]
    val nxt = a[1]
    val n = a[2]
    val d = nxt - head
    println(n * head + n * (n - 1) * d / 2)
}

fun P2788() {
    val result = readLine()!!
        .replace("[+-]".toRegex()) { " " + it.value }
        .split(" ")
    result.toMutableList().removeFirst()
    result.println()
}

fun getPi(n: Int): Double {
    var numInCircle = 0
    var x: Double
    var y: Double
    runBlocking {
        repeat(n) {
            launch {
                x = Math.random()
                y = Math.random()
                if (x * x + y * y < 1) numInCircle++
            }
        }
    }

    return (4.0 * numInCircle) / n
}

@Deprecated(message = "Has a better function", replaceWith = ReplaceWith("measureTimeMillis", "block: () -> Unit"))
fun runWithTime(block: () -> Unit): Long {
    val timer = MSTimer()
    timer.reset()
    block()
    return timer.getLeftTime()
}