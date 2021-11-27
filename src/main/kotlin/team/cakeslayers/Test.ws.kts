/*
 * Copyright (c) 2021 CakeSlayers Reversing Team. All Rights Reserved.
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
 * This file was created by SagiriXiguajerry at 2021/10/16 上午1:04
 */

package team.cakeslayers

import kotlin.reflect.KClass

println("Hello World")

println("Hello World")

1 + 1

2 + 2

0.1 + 0.1

0.1 + 0.1

0.3 + 0.3

4 + 4

"UPPERCASE".lowercase()

inline fun <reified T : Any> getGenericType(t: T): KClass<T> = T::class

getGenericType("abc")

getGenericType("LOL")

1 + 1

2 + 1

2::class

Int::class

"abc"::class

String::class

String::class

"abc"::class

String::class.constructors

