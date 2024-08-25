package com.coreduo.obbah.packet.handshake

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 2033)
class AvailabilityStatusPacket : HabboPacket() {
    var isOpen: Boolean = false
    var isShuttingDown: Boolean = false
    var isAuthenticUser: Boolean = false

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        isOpen = readBoolean()
        isShuttingDown = readBoolean()
        isAuthenticUser = readBoolean()
    }

    override fun serialize(): ByteArray {
        writeBoolean(isOpen)
        writeBoolean(isShuttingDown)
        writeBoolean(isAuthenticUser)
        return super.serialize()
    }
}