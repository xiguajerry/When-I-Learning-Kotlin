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
@file:JvmName("ASMUtils")
@file:Suppress("UNUSED")

package team.cakeslayers.xiguajerry.utils

import jdk.internal.org.objectweb.asm.ClassReader
import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.ClassWriter.COMPUTE_FRAMES
import jdk.internal.org.objectweb.asm.ClassWriter.COMPUTE_MAXS
import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode
import jdk.internal.org.objectweb.asm.tree.ClassNode
import jdk.internal.org.objectweb.asm.tree.MethodInsnNode
import jdk.internal.org.objectweb.asm.tree.MethodNode

fun MethodNode.findMethodInsn(opcode: Int, owner: String, name: String, desc: String): AbstractInsnNode? {
    this.instructions.toArray().forEach { insn ->
        if (insn is MethodInsnNode) {
            if (insn.opcode == opcode && insn.owner == owner && insn.name == name && insn.desc == desc) {
                return insn
            }
        }
    }
    return null
}

fun ClassNode.toBytes(): ByteArray {
    val writer = ClassWriter(COMPUTE_MAXS or COMPUTE_FRAMES)
    this.accept(writer)
    return writer.toByteArray()
}

fun ClassNode.findMethod(name: String, desc: String): MethodNode? {
    this.methods.forEach { methodNode ->
        if (methodNode.name == name && methodNode.desc == desc) {
            return methodNode
        }
    }
    return null
}

fun ByteArray.getNode(): ClassNode {
    val classNode = ClassNode()
    val reader = ClassReader(this)
    reader.accept(classNode, 0)
    return classNode
}