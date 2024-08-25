package com.coreduo.obbah.packet.handshake

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 2419)
class SecurityTicketPacket : HabboPacket() {
    var ticket: String = ""
    var time: Int = 0
    var encryptedTicket: String = ""

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        ticket = readString()
        time = readInt()
        encryptedTicket = readString()
    }

    override fun serialize(): ByteArray {
        clear()
        writeString(ticket)
        writeInt(time)
        writeString(encryptedTicket)
        return super.serialize()
    }
}