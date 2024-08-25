package com.coreduo.obbah.packet.handshake

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 4000)
class ReleaseVersionPacket : HabboPacket() {
    var releaseVersion: String = ""
    var type: String = ""
    var platform: Int = 0
    var category: Int = 0

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        releaseVersion = readString()
        type = readString()
        platform = readInt()
        category = readInt()
    }

    override fun serialize(): ByteArray {
        clear()

        writeString(releaseVersion)
        writeString(type)
        writeInt(platform)
        writeInt(category)
        return super.serialize()
    }
}