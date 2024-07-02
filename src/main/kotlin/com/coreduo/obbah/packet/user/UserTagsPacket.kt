package com.coreduo.obbah.packet.user

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 1255)
class UserTagsPacket : HabboPacket() {
    @PacketField(order = 1)
    var roomUnitId: Int = -1

    @PacketField(order = 2)
    var tags: Array<String> = arrayOf()
}