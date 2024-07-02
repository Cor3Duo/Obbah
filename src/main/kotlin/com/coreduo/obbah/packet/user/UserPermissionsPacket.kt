package com.coreduo.obbah.packet.user

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 411)
class UserPermissionsPacket : HabboPacket() {
    @PacketField(order = 1)
    var clubLevel: Int = 0

    @PacketField(order = 2)
    var securityLevel: Int = 0

    @PacketField(order = 3)
    var isAmbassador: Boolean = false
}