package com.coreduo.obbah.packet.handshake

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 2490)
class UniqueMachineIDPacket : HabboPacket() {
    @PacketField(order = 1)
    var machineId: String = ""

    @PacketField(order = 2)
    var fingerprint: String = ""
}