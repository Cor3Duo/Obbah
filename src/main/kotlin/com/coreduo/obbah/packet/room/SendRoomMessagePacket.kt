package com.coreduo.obbah.packet.room

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 1314)
class SendRoomMessagePacket : HabboPacket() {
    var message: String = ""
    var styledId: Int = 0

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        message = readString()
        styledId = readInt()
    }

    override fun serialize(): ByteArray {
        clear()
        writeString(message)
        writeInt(styledId)
        return super.serialize()
    }
}