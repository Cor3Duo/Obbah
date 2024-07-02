package com.coreduo.obbah.packet.handshake

import com.coreduo.obbah.binary.PacketField
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 2419)
class SecurityTicketPacket : HabboPacket() {
    @PacketField(order = 1)
    var ticket: String = ""

    @PacketField(order = 2)
    var time: Int = 0

    @PacketField(order = 3)
    var encryptedTicket: String = ""
}