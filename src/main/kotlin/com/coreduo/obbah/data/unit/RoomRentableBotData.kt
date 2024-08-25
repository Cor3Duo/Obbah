package com.coreduo.obbah.data.unit

import java.nio.ByteBuffer

class RoomRentableBotData : RoomUnitData() {
    override var type = 4
    var sex: String = ""
    var ownerId: Int = -1
    var ownerName: String = ""
    var skills: MutableList<Short> = mutableListOf()

    override fun deserialize(data: ByteBuffer) {
        super.deserialize(data)

        sex = readString()
        ownerId = readInt()
        ownerName = readString()
        val skillsCount = readInt()
        for (i in 0 until skillsCount) {
            skills.add(readShort())
        }
    }

    override fun serialize(): ByteArray {
        super.serialize()
        writeString(sex)
        writeInt(ownerId)
        writeString(ownerName)
        writeInt(skills.size)
        for (skill in skills) {
            writeShort(skill)
        }

        return getData()
    }
}