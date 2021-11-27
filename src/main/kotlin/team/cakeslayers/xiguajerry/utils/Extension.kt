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

@file:JvmName("Extend")
@file:Suppress("UNUSED", "DEPRECATION")

package team.cakeslayers.xiguajerry.utils

import java.awt.Image
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon
import java.util.*
import java.util.logging.Logger

@Throws(IndexOutOfBoundsException::class)
fun <E> MutableList<E>.removeAtWithReturn(index: Int): MutableList<E> {
    this.removeAt(index)
    return this
}

/**
 * If there is a [Class] that named after [name], the function will return it;
 * If there isn't such a [Class] or a [Throwable] has been thrown, the function will return null.
 * @param name The name of the class.
 * @return An instance of [Class] or null
 * @author SagiriXiguajerry
 */
fun forNameOrNull(name: String): Class<*>? {
    return try {
        Class.forName(name)
    } catch (e: ClassNotFoundException) {
        /* Class not found, return null */
        null
    } catch (e: Throwable) {
        /* A Thowable has been throw, return null */
        e.printStackTrace()
        null
    }
}

fun String.Companion.join(delimiter: CharSequence, vararg elements: CharSequence): String {
    val joiner = StringJoiner(delimiter)
    val var3: Array<out CharSequence> = elements
    val var4: Int = elements.size

    for (var5 in 0 until var4) {
        val cs = var3[var5]
        joiner.add(cs)
    }

    return joiner.toString()
}

fun String.Companion.join(delimiter: CharSequence, elements: Iterable<CharSequence>): String {
    val joiner = StringJoiner(delimiter)
    val var3: Iterator<*> = elements.iterator()

    while (var3.hasNext()) {
        val cs = var3.next() as CharSequence
        joiner.add(cs)
    }

    return joiner.toString()
}

fun String.firstLetter() = this[0]

fun Array<String>.toCompleteString(start: Int = 0): String {
    if (this.size <= start) return ""

    return String.join(" ", *this.copyOfRange(start, this.size))
}

fun Logger.fatal(message: String) = this.severe(message)

infix fun Boolean.assert(boolean: Boolean) {
    if (this != boolean) throw AssertionError()
}

inline infix fun <T> T.assert(block: T.() -> Boolean) {
    if (!block(this)) throw AssertionError()
}

fun <T> T.print(): T {
    print(this)
    return this
}

inline fun <T> T.print(block: (T) -> String): T {
    print(block(this))
    return this
}

fun <T> T.println(): T {
    println(this)
    return this
}

inline fun <T> T.println(block: (T) -> String): T {
    println(block(this))
    return this
}

inline fun <T> T.printToToast(title: String, type: TrayIcon.MessageType, block: (T) -> String) = this?.printToToast(
    Toolkit.getDefaultToolkit().getImage(""),
    title,
    block(this),
    type
)

inline fun <T> T.printToToast(title: String, block: (T) -> String) = this?.printToToast(
    Toolkit.getDefaultToolkit().getImage(""),
    title,
    block(this),
    TrayIcon.MessageType.NONE
)

fun Any.printToToast(image: Image, headMessage: String, message: String, type: TrayIcon.MessageType) {
    val tray: SystemTray = SystemTray.getSystemTray()
    val trayIcon = TrayIcon(image, "")
    try {
        tray.add(trayIcon)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    trayIcon.displayMessage(headMessage, message, type)
    tray.remove(trayIcon)
}

fun Any.printToToast(title: String, type: TrayIcon.MessageType) {
    printToToast(
        Toolkit.getDefaultToolkit().getImage(""),
        title,
        this.toString(),
        type
    )
}

fun Any.printToToast() {
    printToToast(
        Toolkit.getDefaultToolkit().getImage(""),
        "A message",
        this.toString(),
        TrayIcon.MessageType.NONE
    )
}

fun <E> List<E>.getRandomElement(): E = this[(0 until this.size).random()]

fun Int.isPrimer() = (2 until this).map { this % it }.none { it == 0 }

fun Boolean.toInt() = if (this) 1 else 0

fun Throwable.getFullStackTrace() = buildString {
    append("Caused by ${this@getFullStackTrace::class.java.name}: ${this@getFullStackTrace.message}\n")
    for (s in this@getFullStackTrace.stackTrace) {
        append("\tat ${s.className}:${s.lineNumber}\n")
    }
}

fun Throwable.getHalfStackTrace() = buildString {
    append("${this@getHalfStackTrace::class.java.name}: ${this@getHalfStackTrace.message}\n")
    for (s in this@getHalfStackTrace.stackTrace) {
        append("\tat ${s.className}:${s.lineNumber}\n")
    }
}