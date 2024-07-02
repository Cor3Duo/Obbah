package com.coreduo.obbah.packet.room

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 3320)
class RoomWalkRequestPacket : HabboPacket() {
    @PacketField(order = 1)
    var x: Int = -1

    @PacketField(order = 1)
    var y: Int = -1
}