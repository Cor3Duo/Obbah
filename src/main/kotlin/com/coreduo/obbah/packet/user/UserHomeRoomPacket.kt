package com.coreduo.obbah.packet.user

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 2875)
class UserHomeRoomPacket : HabboPacket() {
    var homeRoomId: Int = -1
    var roomIdToEnter: Int = -1

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        homeRoomId = readInt()
        roomIdToEnter = readInt()
    }

    override fun serialize(): ByteArray {
        clear()
        writeInt(homeRoomId)
        writeInt(roomIdToEnter)

        return super.serialize()
    }
}