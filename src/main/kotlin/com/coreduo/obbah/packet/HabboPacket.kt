package com.coreduo.obbah.packet

import com.coreduo.obbah.binary.Serializer
import java.nio.ByteBuffer
import kotlin.reflect.full.findAnnotation

open class HabboPacket {

    fun compose(): ByteArray {
        val header = this::class.findAnnotation<PacketHeader>()?.header
            ?: throw IllegalArgumentException("PacketHeader annotation missing")

        val bytes = Serializer.compose(this)
        val buffer = ByteBuffer.allocate(6 + bytes.size)
        buffer.putInt(bytes.size + 2)
        buffer.putShort(header)
        buffer.put(bytes)

        buffer.flip()
        val byteArray = ByteArray(buffer.remaining())
        buffer.get(byteArray)
        return byteArray
    }

}