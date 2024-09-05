package com.coreduo.obbah.data.room.furniture.types

import com.coreduo.obbah.packet.HabboBuffer
import java.nio.ByteBuffer

open class ObjectDataBase : HabboBuffer() {

    var flags: Int = -1
    var uniqueNumber: Int = -1
    var uniqueSeries: Int = -1

    open fun deserialize(data: ByteBuffer) {
        buffer = data

        flags = readInt()
        if ((flags and 256) != 0) {
            uniqueNumber = readInt()
            uniqueSeries = readInt()
        }
    }

    open fun serialize(): ByteArray {
        clear()

        writeInt(flags)
        if ((flags and 256) != 0) {
            writeInt(uniqueNumber)
            writeInt(uniqueSeries)
        }

        return getData()
    }

    fun serialize(clean: Boolean = false): ByteArray {
        if (clean) {
            clear()
        }

        writeInt(flags)
        if ((flags and 256) != 0) {
            writeInt(uniqueNumber)
            writeInt(uniqueSeries)
        }

        return getData()
    }

    open fun getLegacyString(): String {
        return ""
    }

}