package com.coreduo.obbah.data.room.furniture

import com.coreduo.obbah.data.room.furniture.types.*
import com.coreduo.obbah.packet.HabboBuffer
import java.nio.ByteBuffer

class RoomFloorItem : HabboBuffer() {
    var id: Int = 0
    var spriteId: Int = 0
    var x: Int = 0
    var y: Int = 0
    var rotation: Int = 0
    var z: Float = 0f
    var stackHeight: Float = 0f
    var extra: Int = 0
    var data: ObjectDataBase? = null
    var state: Int = 0
    var expires: Int = 0
    var usagePolicy: Int = 0
    var userId: Int = 0
    var username: String = ""
    var spriteName: String = ""

    fun deserialize(data: ByteBuffer) {
        buffer = data

        extra = readInt()
        id = readInt()
        spriteId = readInt()
        x = readInt()
        y = readInt()
        rotation = readInt()
        z = readString().toFloat()
        stackHeight = readString().toFloat()
        this.data = ObjectDataFactory.getDataType(readInt())
        if (this.data != null) {
            this.data!!.deserialize(buffer)
        }
        state = this.data!!.getLegacyString().toInt()
        expires = readInt()
        usagePolicy = readInt()
        userId = readInt()

        if (spriteId < 0) {
            spriteName = readString()
        }
    }

    fun serialize(): ByteArray {
        clear()

        writeInt(extra)
        writeInt(id)
        writeInt(spriteId)
        writeInt(x)
        writeInt(y)
        writeInt(rotation)
        writeString(z.toString())
        writeString(stackHeight.toString())
        when (data) {
            is LegacyDataType -> {
                writeInt(data!!.flags)
            }
            is MapDataType -> {
                writeInt(0x0100 or data!!.flags)
            }
            is StringDataType -> {
                writeInt(0x0200 or data!!.flags)
            }
            is VoteDataType -> {
                writeInt(0x0300 or data!!.flags)
            }
            is EmptyDataType -> {
                writeInt(0x0400 or data!!.flags)
            }
            is NumberDataType -> {
                writeInt(0x0500 or data!!.flags)
            }
            is HighScoreDataType -> {
                writeInt(0x0600 or data!!.flags)
            }
            is CrackableDataType -> {
                writeInt(0x0700 or data!!.flags)
            }
            else -> {
                throw Exception("Unknown data type")
            }
        }
        data!!.serialize()
        writeInt(state)
        writeInt(expires)
        writeInt(usagePolicy)
        writeInt(userId)
        if (spriteId < 0) {
            writeString(spriteName)
        }

        return getData()
    }
}