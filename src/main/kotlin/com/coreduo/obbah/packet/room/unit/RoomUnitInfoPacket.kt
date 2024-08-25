package com.coreduo.obbah.packet.room.unit

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 3920)
class RoomUnitInfoPacket : HabboPacket() {
    var unitId: Int = -1
    var figure: String = ""
    var gender: String = ""
    var motto: String = ""
    var achievementScore: Int = -1

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        unitId = readInt()
        figure = readString()
        gender = readString()
        motto = readString()
        achievementScore = readInt()
    }

    override fun serialize(): ByteArray {
        clear()

        writeInt(unitId)
        writeString(figure)
        writeString(gender)
        writeString(motto)
        writeInt(achievementScore)
        return super.serialize()
    }
}