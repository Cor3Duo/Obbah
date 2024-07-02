package com.coreduo.obbah.packet.handshake

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 2033)
class AvailabilityStatusPacket : HabboPacket() {
    @PacketField(order = 1)
    var isOpen: Boolean = false

    @PacketField(order = 2)
    var isShuttingDown: Boolean = false

    @PacketField(order = 3)
    var isAuthenticUser: Boolean = false
}