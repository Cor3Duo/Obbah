package com.coreduo.obbah.data.room.furniture.types

import java.nio.ByteBuffer

class LegacyDataType : ObjectDataBase() {
    var data: String = ""

    override fun deserialize(data: ByteBuffer) {
        buffer = data

        this.data = readString()

        super.deserialize(data)
    }

    override fun serialize(): ByteArray {
        clear()

        writeString(data)

        return serialize(true)
    }

    override fun getLegacyString(): String {
        return data
    }
}