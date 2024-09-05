package com.coreduo.obbah.packet

import java.nio.ByteBuffer
import kotlin.reflect.full.findAnnotation

open class HabboPacket : HabboBuffer() {

    init {
        clear()
    }

    final override fun clear() {
        buffer.clear()

        val header = this::class.findAnnotation<PacketHeader>()?.header
            ?: throw IllegalArgumentException("PacketHeader annotation missing")

        writeInt(0)
        writeShort(header)
    }

    open fun deserialize(data: ByteArray) {
        buffer = ByteBuffer.wrap(data)
    }

    open fun serialize(): ByteArray {
        val pos = buffer.position()
        buffer.position(0)
        writeInt(pos - 4)
        buffer.position(pos)

        return getData()
    }

}