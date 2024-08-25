package com.coreduo.obbah.packet.handshake

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 1488)
class UniqueMachineIDResponsePacket : HabboPacket() {
    var machineID: String = ""

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        machineID = readString()
    }

    override fun serialize(): ByteArray {
        writeString(machineID)
        return super.serialize()
    }
}