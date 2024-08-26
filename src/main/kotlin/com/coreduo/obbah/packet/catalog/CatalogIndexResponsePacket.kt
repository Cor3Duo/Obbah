package com.coreduo.obbah.packet.catalog

import com.coreduo.obbah.data.catalog.CatalogNodeData
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(1032)
class CatalogIndexResponsePacket : HabboPacket() {
    var root: CatalogNodeData = CatalogNodeData()
    var newAdditionsAvailable: Boolean = false
    var catalogType: String = ""

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)
        root.deserialize(buffer)
        newAdditionsAvailable = readBoolean()
        catalogType = readString()
    }

    override fun serialize(): ByteArray {
        clear()
        writeBytes(root.serialize())
        writeBoolean(newAdditionsAvailable)
        writeString(catalogType)
        return super.serialize()
    }
}