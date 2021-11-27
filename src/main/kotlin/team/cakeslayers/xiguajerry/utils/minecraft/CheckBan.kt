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

@file:JvmName("CheckBan")
@file:Suppress("UNUSED", "DEPRECATION")

package team.cakeslayers.xiguajerry.utils.minecraft

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.apache.commons.io.IOUtil
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients

import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.swing.JOptionPane

@Throws(Throwable::class)
fun getBansourse(player: String, banID: String): String {
    val uuid = player.trim()
    val http = "https://hypixel.net/api/players/$uuid"
    val closeableHttpClient: CloseableHttpClient = HttpClients.createDefault()
    val httpGet = HttpGet(http)
    httpGet.setHeader(
        "user-agent",
        "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Mobile Safari/537.36"
    )
    httpGet.setHeader("xf-api-key", "kB3PlymjFqMbQA-KhdJ5N5DcxBajLziW")
    var finalResult = ""
    try {
        try {
            val response: CloseableHttpResponse = closeableHttpClient.execute(httpGet)
            response.use { targetResponse ->
                finalResult = IOUtil.toString(targetResponse.entity.content, StandardCharsets.UTF_8.toString())
            }
        } catch (e: Throwable) {
            throw e
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: Throwable) {
        e.printStackTrace()
    }

    val gson1 = Gson()
    val array = gson1.fromJson(finalResult.trim(), JsonObject::class.java)
    val finalID = banID.replace("#", "")
    val accessLocation = "https://hypixel.net/api/players/${array.get("uuid").asString}/ban/$finalID"
    val closeableHttpClient1 = HttpClients.createDefault()
    val newHttp = HttpGet(accessLocation)
    newHttp.setHeader(
        "user-agent",
        "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Mobile Safari/537.36"
    )
    newHttp.setHeader("xf-api-key", "kB3PlymjFqMbQA-KhdJ5N5DcxBajLziW")

    var newCloseableHttpClient: CloseableHttpClient
    try {
        try {
            val response = closeableHttpClient1.execute(newHttp)

            response.use {
                finalResult = IOUtil.toString(it.entity.content, StandardCharsets.UTF_8.toString())
            }
        } catch (e: Throwable) {
            throw e
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    while (finalResult.contains("too_many_request") && JOptionPane.showConfirmDialog(null, "访问频繁, 按确定继续尝试获取!") == 1) {
        val string4111 = "https://hypixel.net/api/players/${array.get("uuid").asString}/ban/$finalID"
        newCloseableHttpClient = HttpClients.createDefault()
        val anoHttp = HttpGet(string4111)
        anoHttp.setHeader(
            "user-agent",
            "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Mobile Safari/537.36"
        )
        anoHttp.setHeader("xf-api-key", "kB3PlymjFqMbQA-KhdJ5N5DcxBajLziW")

        try {
            try {
                val closeableHttpResponse = newCloseableHttpClient.execute(anoHttp)

                closeableHttpResponse.use { closeableHttpResponse2 ->
                    finalResult =
                        IOUtil.toString(closeableHttpResponse2.entity.content, StandardCharsets.UTF_8.toString())
                }
            } catch (e: Throwable) {
                throw e
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return getWatchdogString(finalResult)
}

fun getBanID(messages: List<String>): String {
    for (message in messages) {
        if (message.contains("#")) {
            return message.substring(message.length - 9, message.length)
        }
    }
    return ""
}

private fun getWatchdogString(load: String): String {
    val webLines = load.split(",").toTypedArray()
    for (current in webLines) {
        if (current.contains("subType")) {
            val a = '"'.toString()
            return current.replace("\n    ", "").replace(a, "").replace("subType: ", "")
        }
    }
    return ""
}