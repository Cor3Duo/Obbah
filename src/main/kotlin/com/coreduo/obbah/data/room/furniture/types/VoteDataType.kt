package com.coreduo.obbah.data.room.furniture.types

import java.nio.ByteBuffer

class VoteDataType : ObjectDataBase() {
    var state: String = ""
    var result: Int = 0

    override fun deserialize(data: ByteBuffer) {
        buffer = data

        state = readString()
        result = readInt()

        super.deserialize(data)
    }

    override fun serialize(): ByteArray {
        clear()

        writeString(state)
        writeInt(result)

        return serialize(true)
    }

    override fun getLegacyString(): String {
        return state
    }
}