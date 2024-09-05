package com.coreduo.obbah.data.room.furniture.types

import java.nio.ByteBuffer

class CrackableDataType : ObjectDataBase() {
    var state: String = ""
    var hits: Int = -1
    var target: Int = -1

    override fun deserialize(data: ByteBuffer) {
        buffer = data

        state = readString()
        hits = readInt()
        target = readInt()

        super.deserialize(data)
    }

    override fun serialize(): ByteArray {
        clear()

        writeString(state)
        writeInt(hits)
        writeInt(target)

        return serialize(true)
    }

    override fun getLegacyString(): String {
        return state
    }
}