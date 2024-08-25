package com.coreduo.obbah.data.unit

import com.coreduo.obbah.packet.HabboBuffer
import java.nio.ByteBuffer

open class RoomUnitData : HabboBuffer() {
    var id: Int = -1
    var username: String = ""
    var custom: String = ""
    var figure: String = ""
    var roomIndex: Int = -1
    var x: Int = -1
    var y: Int = -1
    var z: String = ""
    var direction: Int = -1
    open var type: Int = -1

    open fun deserialize(data: ByteBuffer) {
        buffer = data

        id = readInt()
        username = readString()
        custom = readString()
        figure = readString()
        roomIndex = readInt()
        x = readInt()
        y = readInt()
        z = readString()
        direction = readInt()
        type = readInt()
    }

    open fun serialize(): ByteArray {
        clear()

        writeInt(id)
        writeString(username)
        writeString(custom)
        writeString(figure)
        writeInt(roomIndex)
        writeInt(x)
        writeInt(y)
        writeString(z)
        writeInt(direction)
        writeInt(type)

        return getData()
    }
}