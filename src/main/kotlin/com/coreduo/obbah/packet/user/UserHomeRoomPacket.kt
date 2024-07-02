package com.coreduo.obbah.packet.user

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 2875)
class UserHomeRoomPacket : HabboPacket() {
    @PacketField(order = 1)
    var homeRoomId: Int = -1

    @PacketField(order = 2)
    var roomIdToEnter: Int = -1
}