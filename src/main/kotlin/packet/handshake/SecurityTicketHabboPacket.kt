package packet.handshake

import binary.PacketField
import packet.HabboPacket

class SecurityTicketHabboPacket : HabboPacket() {
    @PacketField(order = 1)
    val headerId: Short = 2419

    @PacketField(order = 2)
    var ticket: String = ""

    @PacketField(order = 3)
    var time: Int = 0

    @PacketField(order = 4)
    var encryptedTicket: String = ""
}