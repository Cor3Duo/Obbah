package com.coreduo.obbah.data.room.furniture.types

import java.nio.ByteBuffer

class StringDataType : ObjectDataBase() {
    var data: MutableList<String> = mutableListOf()

    companion object {
        private val STATE: Int = 0
    }

    override fun deserialize(data: ByteBuffer) {
        buffer = data

        val size = readInt()
        for (i in 0 until size) {
            this.data.add(readString())
        }

        super.deserialize(data)
    }

    override fun serialize(): ByteArray {
        clear()

        writeInt(data.size)
        for (value in data) {
            writeString(value)
        }

        return serialize(true)
    }

    override fun getLegacyString(): String {
        if (data.isEmpty()) {
            return ""
        }

        val state = data[STATE] ?: return ""
        return state
    }
}