package com.coreduo.obbah.packet.room

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 3320)
class RoomWalkRequestPacket : HabboPacket() {
    var x: Int = -1
    var y: Int = -1

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        x = readInt()
        y = readInt()
    }

    override fun serialize(): ByteArray {
        clear()
        writeInt(x)
        writeInt(y)
        return super.serialize()
    }
}