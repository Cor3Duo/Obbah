package com.coreduo.obbah.packet.room.unit

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.data.unit.*
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 374)
class RoomUnitPacket : HabboPacket() {
    @PacketField(order = 1)
    var units: Array<RoomUnitData> = arrayOf()
}