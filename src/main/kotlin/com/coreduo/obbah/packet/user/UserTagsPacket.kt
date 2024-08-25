package com.coreduo.obbah.packet.user

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 1255)
class UserTagsPacket : HabboPacket() {
    var roomUnitId: Int = -1
    var tags: MutableList<String> = mutableListOf()

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        roomUnitId = readInt()
        val tagsCount = readInt()
        for (tag in 0 until tagsCount) {
            tags.add(readString())
        }
    }

    override fun serialize(): ByteArray {
        clear()
        writeInt(roomUnitId)
        writeInt(tags.size)
        for (tag in tags) {
            writeString(tag)
        }

        return super.serialize()
    }
}