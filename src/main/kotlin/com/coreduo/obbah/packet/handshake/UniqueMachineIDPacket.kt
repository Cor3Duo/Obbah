package com.coreduo.obbah.packet.handshake

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 2490)
class UniqueMachineIDPacket : HabboPacket() {
    var machineId: String = ""
    var fingerprint: String = ""

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        machineId = readString()
        fingerprint = readString()
    }

    override fun serialize(): ByteArray {
        clear()
        writeString(machineId)
        writeString(fingerprint)
        return super.serialize()
    }
}