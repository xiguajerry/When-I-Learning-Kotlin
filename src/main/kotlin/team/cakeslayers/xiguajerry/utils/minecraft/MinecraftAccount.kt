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

package team.cakeslayers.xiguajerry.utils.minecraft

class MinecraftAccount(_name: String?, _password: String?, _inGameName: String?) {
    private var username: String? = null
    private var password: String? = null
    private var inGameName: String? = null

    init {
        this.username = _name
        this.password = _password
        this.inGameName = _inGameName
    }

    constructor(name: String?, password: String?) : this(name, password, null)

    constructor(userName: String?) : this(userName, null, null)

    fun isCracked(): Boolean {
        return password == null || password!!.isEmpty()
    }

    fun getName(): String? {
        return username
    }

    fun getPassword(): String? {
        return password
    }

    fun getAccountName(): String? {
        return inGameName
    }

    fun setAccountName(accountName: String?) {
        inGameName = accountName
    }
}