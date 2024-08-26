package com.coreduo.obbah.packet.catalog

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(1195)
class CatalogIndexRequestPacket : HabboPacket() {
    var mode: String = ""

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        mode = readString()
    }

    override fun serialize(): ByteArray {
        clear()
        writeString(mode)
        return super.serialize()
    }
}