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

package team.cakeslayers.xiguajerry.legacy

inline fun handleString(block: (String) -> Unit) {
    block("Hello")
}

fun printString(str: String) {
    println(str)
}

inline fun <reified T> getGenericType() = T::class.java

open class Person(val name: String, val age: Int)
class Student(name: String, age: Int) : Person(name, age)
class Teacher(name: String, age: Int) : Person(name, age)

class SimpleData<out T>(private val data: T?) {
    fun get() = data
}

fun handleSimpleData(data: SimpleData<Person>) {
    @Suppress("unused_parameter") val personData = data.get()
}

interface Transformer<in T> {
    fun transform(name: String, age: Int): @UnsafeVariance T
}

fun main(args: Array<String>) {
    val trans = object : Transformer<Person> {
        override fun transform(name: String, age: Int): Person {
            return Teacher(name, age)
        }
    }
    handleTransformer(trans)
}

fun handleTransformer(trans: Transformer<Student>) {
    @Suppress("unused_parameter") val result = trans.transform("Tom", 19)
}
