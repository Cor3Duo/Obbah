package com.coreduo.obbah.packet.room.access

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 2312)
class EnterRoomRequestPacket : HabboPacket() {
    @PacketField(order = 1)
    var roomId: Int = 0

    @PacketField(order = 2)
    var password: String = ""
}