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

@file:JvmName("ReflectionUtils")
@file:Suppress("UNUSED", "DEPRECATION", "unused_parameter")

package team.cakeslayers.xiguajerry.utils

import org.reflections.Reflections
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KVariance
import kotlin.reflect.KVisibility.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.extensionReceiverParameter
import kotlin.reflect.full.functions
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.javaType

/**
 * Get some information from a class.Use Java Reflection API.
 * @param clz [Class]
 */
fun viewClass(clz: Class<*>): String {
    return buildString {
        val p = clz.`package`
        append("package ${p.name};\n")

        val modifier = clz.modifiers
        append("${Modifier.toString(modifier)} ${if (Modifier.isInterface(modifier)) "interface" else "class"} ${clz.name} { \n")

        val fields = clz.declaredFields
        fields.forEach {
            append("\t${Modifier.toString(it.modifiers)} ${it.type.name} ${it.name};\n")
        }

        val constructors = clz.constructors
        constructors.forEach {
            append("\t${Modifier.toString(it.modifiers)} ${it.name}();\n")
        }

        val methods = clz.declaredMethods
        methods.forEach {
            append("\t${Modifier.toString(it.modifiers)} ${it.returnType} ${it.name}() { /* compiled code */ }\n")
        }

        append("}")
    }
}

/**
 * Get some information from a class.Use Kotlin Reflection API.
 * @param clazz [KClass]
 */
fun viewKClass(clazz: KClass<*>) = buildString {
    append("package ${clazz.java.`package`.name}\n\n")

    when (clazz.visibility) {
        PUBLIC -> append("public ")
        PRIVATE -> append("private ")
        INTERNAL -> append("internal ")
        PROTECTED -> append("protected ")
        null -> append("null ")
        else -> append("")
    }

    if (clazz.isFinal && !clazz.isAbstract) {
        append("final ")
    } else if (!clazz.isSealed) {
        append("open ")
    }

    if (clazz.isSealed) append("sealed ")

    if (clazz.isAbstract) append("abstract ")

    append("class ")

    append(clazz.simpleName)

    //Main constructor
    clazz.primaryConstructor?.let { kFunction ->
        if (kFunction.typeParameters.isNotEmpty()) {
            append("<")
            kFunction.typeParameters.forEachIndexed { index, kTypeParameter ->
                if (index != kFunction.typeParameters.size - 1) {
                    if (kTypeParameter.isReified) append("reified ")
                    when (kTypeParameter.variance) {
                        KVariance.INVARIANT -> append("")
                        KVariance.IN -> append("in ")
                        KVariance.OUT -> append("out")
                    }
                    append(kTypeParameter.name)
                    append(", ")
                } else {
                    if (kTypeParameter.isReified) append("reified ")
                    when (kTypeParameter.variance) {
                        KVariance.INVARIANT -> append("")
                        KVariance.IN -> append("in ")
                        KVariance.OUT -> append("out")
                    }
                    append(kTypeParameter.name)
                }
            }
            append("> ")
        }
        append("(")
        if (kFunction.parameters.isNotEmpty()) {
            kFunction.parameters.forEachIndexed { index, it ->
                if (index != kFunction.parameters.size - 1) {
                    if (it.isVararg) append("vararg ")
                    append(it.name + ": ")
                    append(it.type.javaType.typeName)
                    if (it.type.isMarkedNullable) append("?, ") else append(", ")
                } else {
                    if (it.isVararg) append("vararg ")
                    append(it.name + ": ")
                    append(it.type.javaType.typeName)
                    if (it.type.isMarkedNullable) append("?")
                }
            }
        }
        append(")")
    }

    //head
    append(" {\n\n")

    //fields
    clazz.declaredMemberProperties.forEach { kProperty1 ->
        append("\t")
        when (kProperty1.visibility) {
            PUBLIC -> append("public ")
            PROTECTED -> append("protected ")
            INTERNAL -> append("internal ")
            PRIVATE -> append("private ")
            null -> append("null ")
            else -> append("")
        }
        if (kProperty1.isOpen) append("open ") else append("final ")
        if (kProperty1.isAbstract) append("abstract ")
        if (kProperty1.isConst) append("const ")
        if (kProperty1.isLateinit) append("lateinit ")
        if (kProperty1 is KMutableProperty<*>) append("var ") else append("val ")
        append(kProperty1.name)
        append(": ")
        append(kProperty1.returnType)
        append("\n\n")
    }

    //constructors
    clazz.constructors.forEach { kFunction ->
        kFunction.annotations.forEach { annotation ->
            append("\t@")
            append(annotation.annotationClass.qualifiedName)
            append("\n")
        }
        when (kFunction.visibility) {
            PUBLIC -> append("\tpublic ")
            PRIVATE -> append("\tprivate ")
            INTERNAL -> append("\tinternal ")
            PROTECTED -> append("\tprotected ")
            null -> append("\tnull ")
            else -> append("\t")
        }
        append("constructor")
        append("(")
        if (kFunction.parameters.isNotEmpty()) {
            for (index in 0 until kFunction.parameters.size) {
                val kParameter = kFunction.parameters[index]
                if (index != kFunction.parameters.size - 1) {
                    if (kParameter.isVararg) append("vararg ")
                    append(kParameter.name + ": ")
                    append(kParameter.type)
                    append(", ")
                } else {
                    if (kParameter.isVararg) append("vararg ")
                    append(kParameter.name + ": ")
                    append(kParameter.type)
                }
            }
        }
        append("): ")
        append(kFunction.returnType)
        append(" {/* compiled code */}\n\n")
    }

    //functions
    clazz.functions.forEach { kFunction ->
        kFunction.annotations.forEach { annotation ->
            append("\t@")
            append(annotation.annotationClass.qualifiedName)
            append("\n")
        }
        when (kFunction.visibility) {
            PUBLIC -> append("\tpublic ")
            PRIVATE -> append("\tprivate ")
            INTERNAL -> append("\tinternal ")
            PROTECTED -> append("\tprotected ")
            null -> append("\tnull ")
            else -> append("\t")
        }
        if (kFunction.isOpen) append("open ") else append("final ")
        if (kFunction.isSuspend) append("suspend ")
        if (kFunction.isInline) append("inline ")
        if (kFunction.isInfix) append("infix ")
        if (kFunction.isAbstract) append("abstract ")
        if (kFunction.isOperator) append("operator ")
        append("fun ")
        if (kFunction.typeParameters.isNotEmpty()) {
            append("<")
            kFunction.typeParameters.forEachIndexed { index, kTypeParameter ->
                if (index != kFunction.typeParameters.size - 1) {
                    if (kTypeParameter.isReified) append("reified ")
                    when (kTypeParameter.variance) {
                        KVariance.INVARIANT -> append("")
                        KVariance.IN -> append("in ")
                        KVariance.OUT -> append("out")
                    }
                    append(kTypeParameter.name)
                    append(", ")
                } else {
                    if (kTypeParameter.isReified) append("reified ")
                    when (kTypeParameter.variance) {
                        KVariance.INVARIANT -> append("")
                        KVariance.IN -> append("in ")
                        KVariance.OUT -> append("out")
                    }
                    append(kTypeParameter.name)
                }
            }
            append("> ")
        }
        if (kFunction.isExternal) append(kFunction.extensionReceiverParameter!!.name)
        append(kFunction.name + "(")
        if (kFunction.parameters.isNotEmpty()) {
            for (index in 1 until kFunction.parameters.size) {
                val kParameter = kFunction.parameters[index]
                if (index != kFunction.parameters.size - 1) {
                    if (kParameter.isVararg) append("vararg ")
                    append(kParameter.name + ": ")
                    append(kParameter.type)
                    append(", ")
                } else {
                    if (kParameter.isVararg) append("vararg ")
                    append(kParameter.name + ": ")
                    append(kParameter.type)
                }
            }
        }
        append("): ")
        append(kFunction.returnType)
        append(" {/* compiled code */}\n\n")
    }

    //end
    append("}")
}

/**
 * Get a [Method] from [Class] through reflection, and automatically let it accessible.
 * @param clazz An instance of [Class]
 * @param name Method's name
 * @param args Method's args
 * @return An object of [Method]
 */
fun getMethodFromClazz(clazz: Class<*>, name: String, vararg args: Class<*>): Method? {
    var method: Method? = null
    try {
        method = clazz.getDeclaredMethod(name, *args)
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
    method?.isAccessible = true
    return method
}

/**
 * Get a [Method] from [Class] through reflection, and automatically let it accessible.
 * @param clazzName The name of class
 * @param name Method's name
 * @param args Method's args
 * @return An object of [Method]
 */
fun getMethodFromClazz(clazzName: String, name: String, vararg args: Class<*>): Method? {
    val clazz = Class.forName(clazzName)
    var method: Method? = null
    try {
        method = clazz.getDeclaredMethod(name, *args)
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
    method?.isAccessible = true
    return method
}

/**
 * Automatically get the method which is called [name] from [clazz], and automatically invoke it.
 * @param args The args of the method
 * @param param Will use to invoke the method
 */
fun invokeMethod(clazz: Class<*>, name: String, args: Array<Class<*>>, vararg param: Any): Any? {
    var method: Method? = null
    try {
        method = clazz.getDeclaredMethod(name, *args)
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
    method?.isAccessible = true
    val constructor = clazz.getConstructor()
    return method?.invoke(constructor.newInstance(), *param)
}

fun invokeMethod(clazzName: String, name: String, args: Array<Class<*>>, vararg param: Any): Any? {
    val clazz = Class.forName(clazzName)
    var method: Method? = null
    try {
        method = clazz.getDeclaredMethod(name, *args)
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
    method?.isAccessible = true
    val constructor = clazz.getConstructor()
    return method?.invoke(constructor.newInstance(), *param)
}

fun <T> findClasses(pack: String?, subType: Class<T>?): Set<Class<out T>?>? {
    val reflections = Reflections(pack)
    return reflections.getSubTypesOf(subType)
}

private val cachedClasses = mutableMapOf<String, Boolean>()

/**
 * Allows you to check for existing classes with the [className]
 */
fun hasClass(className: String): Boolean {
    return if (cachedClasses.containsKey(className))
        cachedClasses[className]!!
    else try {
        Class.forName(className)
        cachedClasses[className] = true
        true
    } catch (e: ClassNotFoundException) {
        cachedClasses[className] = false
        false
    }
}

fun hasForge() = hasClass("net.minecraftforge.common.MinecraftForge")