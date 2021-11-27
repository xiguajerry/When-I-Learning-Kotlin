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

package team.cakeslayers.xiguajerry.utils.debugging

import team.cakeslayers.xiguajerry.utils.getFullStackTrace
import java.awt.BorderLayout
import java.awt.Font
import java.awt.event.InputEvent
import java.awt.event.KeyEvent
import java.text.DecimalFormat
import java.util.*
import javax.swing.*

/**
 * 推荐通过SwingUtilities.invokeLater创建实例
 */
class DebugWindow(showtitle: String) : JFrame() {
    init {
        initComponents(showtitle)
        initEventListeners()
    }

    //Menu
    private var menuBar: JMenuBar? = null
    private var controlMenu: JMenu? = null
    private var menuClose: JMenuItem? = null

    //Text
    private var textArea: JTextArea? = null

    private fun getTime(): String {
        val df = DecimalFormat("00")
        val date = Date(System.currentTimeMillis())
        val hours = df.format(date.hours)
        val minutes = df.format(date.minutes)
        val seconds = df.format(date.seconds)
        return "[$hours:$minutes:$seconds]"
    }

    private fun getTime(ms: Long): String {
        val df = DecimalFormat("00")
        val date = Date(ms)
        val hours = df.format(date.hours)
        val minutes = df.format(date.minutes)
        val seconds = df.format(date.seconds)
        return "[$hours:$minutes:$seconds]"
    }

    /**
     * Print a debug message
     * @param message give something you want to send.
     */
    fun d(message: Any) = addMessage("${getTime()}[Debug]$message")

    /**
     * Print an info message
     * @param message give something you want to send.
     */
    fun i(message: Any) = addMessage("${getTime()}[Info]$message")

    /**
     * Print a warning message
     * @param message give something you want to send.
     */
    fun w(message: Any) = addMessage("${getTime()}[Warn]$message")

    /**
     * Print a error message
     * @param message give something you want to send.
     */
    fun e(message: Any) = addMessage("${getTime()}[Error]$message")

    /**
     * Print a fatal message
     * @param message give something you want to send.
     * @param exc An exception or a error
     */
    fun f(message: Any, exc: Throwable) {
        val stackTraceMessage = exc.getFullStackTrace()
        if (exc is Error) JOptionPane.showMessageDialog(
            null,
            "A error has been threw:\n$stackTraceMessage",
            "An error has been threw:",
            JOptionPane.ERROR_MESSAGE
        )
        addMessage("${getTime()}[Fatal]$message\n" + stackTraceMessage)
    }

    fun addMessage(message: String) {
        (textArea ?: return).append("$message\n")
    }

    private fun initComponents(windowtitle: String) {
        title = windowtitle
        setSize(400, 300)

        //Init the menu
        controlMenu = JMenu("Control")
        menuClose = JMenuItem("Close")
        (controlMenu ?: return).add(menuClose)
        menuBar = JMenuBar()
        (menuBar ?: return).add(controlMenu)
        jMenuBar = menuBar

        //Init the text
        textArea = JTextArea()
        textArea!!.font = Font("细明体", Font.PLAIN, 12)
        textArea!!.lineWrap = false
        textArea!!.isEditable = false
        val panel = JScrollPane(
            textArea,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        )
        val contentPane = contentPane
        contentPane.add(panel, BorderLayout.CENTER)
        i("Debug Window Started.")
    }

    private fun initEventListeners() {
        defaultCloseOperation = EXIT_ON_CLOSE
        menuClose!!.accelerator = KeyStroke.getKeyStroke(
            KeyEvent.VK_C,
            InputEvent.CTRL_MASK
        )
        menuClose!!.addActionListener { dispose() }
    }

}