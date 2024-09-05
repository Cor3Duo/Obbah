package com.coreduo.obbah.packet.room.unit

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 1446)
class UnitChatPacket : HabboPacket() {
    var roomIndex: Int = -1
    var message: String = ""
    var gesture: Int = -1
    var bubbleId: Int = -1
    var urls: MutableList<String> = mutableListOf()
    var messageLength: Int = -1

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        roomIndex = readInt()
        message = readString()
        gesture = readInt()
        bubbleId = readInt()
        val urlsCount = readInt()
        for (i in 0 until urlsCount) {
            urls.add(readString())
        }
        messageLength = readInt()
    }

    override fun serialize(): ByteArray {
        clear()

        writeInt(roomIndex)
        writeString(message)
        writeInt(gesture)
        writeInt(bubbleId)
        writeInt(urls.size)
        for (url in urls) {
            writeString(url)
        }
        writeInt(messageLength)

        return super.serialize()
    }
}