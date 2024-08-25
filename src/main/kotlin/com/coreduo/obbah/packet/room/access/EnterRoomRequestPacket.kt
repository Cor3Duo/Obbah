package com.coreduo.obbah.packet.room.access

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 2312)
class EnterRoomRequestPacket : HabboPacket() {
    var roomId: Int = 0
    var password: String = ""

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        roomId = readInt()
        password = readString()
    }

    override fun serialize(): ByteArray {
        clear()
        writeInt(roomId)
        writeString(password)
        return super.serialize()
    }
}