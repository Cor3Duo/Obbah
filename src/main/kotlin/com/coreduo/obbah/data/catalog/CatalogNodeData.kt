package com.coreduo.obbah.data.catalog

import com.coreduo.obbah.packet.HabboBuffer
import java.nio.ByteBuffer

class CatalogNodeData : HabboBuffer() {
    var visible: Boolean = false
    var icon: Int = 0
    var pageId: Int = 0
    var pageName: String = ""
    var localization: String = ""
    var offerIds: MutableList<Int> = mutableListOf()
    var children: MutableList<CatalogNodeData> = mutableListOf()

    fun deserialize(data: ByteBuffer) {
        buffer = data

        visible = readBoolean()
        icon = readInt()
        pageId = readInt()
        pageName = readString()
        localization = readString()

        val totalOffers = readInt()
        for (i in 0 until totalOffers) {
            offerIds.add(readInt())
        }

        val totalChildren = readInt()
        for (i in 0 until totalChildren) {
            val node = CatalogNodeData()
            node.deserialize(buffer)
            children.add(node)
        }
    }

    fun serialize(): ByteArray {
        clear()

        writeBoolean(visible)
        writeInt(icon)
        writeInt(pageId)
        writeString(pageName)
        writeString(localization)
        writeInt(offerIds.size)
        offerIds.forEach({ writeInt(it) })
        writeInt(children.size)
        children.forEach({ writeBytes(it.serialize()) })

        return getData()
    }
}