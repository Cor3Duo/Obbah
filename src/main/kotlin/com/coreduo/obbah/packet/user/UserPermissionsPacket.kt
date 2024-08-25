package com.coreduo.obbah.packet.user

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 411)
class UserPermissionsPacket : HabboPacket() {
    var clubLevel: Int = 0
    var securityLevel: Int = 0
    var isAmbassador: Boolean = false

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        clubLevel = readInt()
        securityLevel = readInt()
        isAmbassador = readBoolean()
    }

    override fun serialize(): ByteArray {
        clear()
        writeInt(clubLevel)
        writeInt(securityLevel)
        writeBoolean(isAmbassador)

        return super.serialize()
    }
}