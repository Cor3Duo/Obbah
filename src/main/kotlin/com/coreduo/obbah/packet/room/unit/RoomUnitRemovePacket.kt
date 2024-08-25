package com.coreduo.obbah.packet.room.unit

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(2661)
class RoomUnitRemovePacket : HabboPacket() {
    var unitId: String = ""

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        unitId = readString()
    }

    override fun serialize(): ByteArray {
        clear()
        writeString(unitId)
        return super.serialize()
    }
}