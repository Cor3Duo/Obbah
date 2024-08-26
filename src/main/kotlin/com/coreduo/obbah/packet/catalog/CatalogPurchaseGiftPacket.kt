package com.coreduo.obbah.packet.catalog

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(1411)
class CatalogPurchaseGiftPacket : HabboPacket() {
    var pageId: Int = 0
    var itemId: Int = 0
    var extraData: String = ""
    var receivingName: String = ""
    var giftMessage: String = ""
    var spriteId: Int = 0
    var boxId: Int = 0
    var ribbonId: Int = 0
    var showMyFace: Boolean = true

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        pageId = readInt()
        itemId = readInt()
        extraData = readString()
        receivingName = readString()
        giftMessage = readString()
        spriteId = readInt()
        boxId = readInt()
        ribbonId = readInt()
        showMyFace = readBoolean()
    }

    override fun serialize(): ByteArray {
        clear()
        writeInt(pageId)
        writeInt(itemId)
        writeString(extraData)
        writeString(receivingName)
        writeString(giftMessage)
        writeInt(spriteId)
        writeInt(boxId)
        writeInt(ribbonId)
        writeBoolean(showMyFace)
        return super.serialize()
    }
}