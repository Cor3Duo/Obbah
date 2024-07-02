package com.coreduo.obbah.packet.room.unit

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 3920)
class RoomUnitInfoPacket : HabboPacket() {
    @PacketField(order = 1)
    var unitId: Int = -1

    @PacketField(order = 2)
    var figure: String = ""

    @PacketField(order = 3)
    var gender: String = ""

    @PacketField(order = 4)
    var motto: String = ""

    @PacketField(order = 5)
    var achievementScore: Int = -1
}