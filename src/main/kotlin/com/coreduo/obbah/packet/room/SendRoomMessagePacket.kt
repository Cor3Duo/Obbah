package com.coreduo.obbah.packet.room

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 1314)
class SendRoomMessagePacket : HabboPacket() {
    @PacketField(order = 1)
    var message: String = ""

    @PacketField(order = 2)
    var styledId: Int = 0
}