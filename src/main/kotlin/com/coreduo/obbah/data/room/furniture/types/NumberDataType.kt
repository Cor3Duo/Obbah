package com.coreduo.obbah.data.room.furniture.types

import java.nio.ByteBuffer

class NumberDataType : ObjectDataBase() {
    var data: MutableList<Int> = mutableListOf()

    companion object {
        private val STATE: Int = 0
    }

    override fun deserialize(data: ByteBuffer) {
        buffer = data

        val size = readInt()
        for (i in 0 until size) {
            this.data.add(readInt())
        }

        super.deserialize(data)
    }

    override fun serialize(): ByteArray {
        clear()

        writeInt(data.size)
        for (value in data) {
            writeInt(value)
        }

        return serialize(true)
    }

    override fun getLegacyString(): String {
        if (data.size == 0) return ""
        return data[STATE].toString()
    }

}