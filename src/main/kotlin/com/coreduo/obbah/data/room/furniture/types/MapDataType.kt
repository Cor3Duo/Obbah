package com.coreduo.obbah.data.room.furniture.types

import java.nio.ByteBuffer

class MapDataType : ObjectDataBase() {
    var data: MutableMap<String, String> = mutableMapOf()

    companion object {
        private val STATE: String = "state"
        private val RARITY: String = "rarity"
    }

    override fun deserialize(data: ByteBuffer) {
        buffer = data

        val size = readInt()
        for (i in 0 until size) {
            val key = readString()
            val value = readString()
            this.data[key] = value
        }

        super.deserialize(data)
    }

    override fun serialize(): ByteArray {
        clear()

        writeInt(data.size)
        for ((key, value) in data) {
            writeString(key)
            writeString(value)
        }

        return serialize(true)
    }

    override fun getLegacyString(): String {
        val state = data[STATE] ?: return ""
        return state
    }
}