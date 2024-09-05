package com.coreduo.obbah.packet.room.unit

import com.coreduo.obbah.data.room.unit.RoomUnitStatusData
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(1640)
class RoomUnitStatusPacket : HabboPacket() {
    var statuses: MutableList<RoomUnitStatusData> = mutableListOf()

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        val statusesCount = readInt()
        for (i in 0 until statusesCount) {
            val status = RoomUnitStatusData()
            status.deserialize(buffer)
            statuses.add(status)
        }
    }

    override fun serialize(): ByteArray {
        clear()
        return super.serialize()
    }
}