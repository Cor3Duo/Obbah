package com.coreduo.obbah.packet.handshake

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 1488)
class UniqueMachineIDResponsePacket : HabboPacket() {
    @PacketField(order = 1)
    var machineID: String = ""
}