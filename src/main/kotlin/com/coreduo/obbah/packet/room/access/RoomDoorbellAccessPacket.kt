package com.coreduo.obbah.packet.room.access

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 1644)
class RoomDoorbellAccessPacket : HabboPacket() {
    var username: String = ""
    var allowEntry: Boolean = false

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        username = readString()
        allowEntry = readBoolean()
    }

    override fun serialize(): ByteArray {
        clear()
        writeString(username)
        writeBoolean(allowEntry)
        return super.serialize()
    }
}