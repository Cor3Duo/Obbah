package com.coreduo.obbah.data.unit

import java.nio.ByteBuffer

class RoomUserData : RoomUnitData() {
    var sex: String = ""
    var groupId: Int = -1
    var groupStatus: Int = -1
    var groupName: String = ""
    var swimFigure: String = ""
    var activityPoints: Int = -1
    var isModerator: Boolean = false

    override fun deserialize(data: ByteBuffer) {
        super.deserialize(data)

        sex = readString()
        groupId = readInt()
        groupStatus = readInt()
        groupName = readString()
        swimFigure = readString()
        activityPoints = readInt()
        isModerator = readBoolean()
    }

    override fun serialize(): ByteArray {
        super.serialize()
        writeString(sex)
        writeInt(groupId)
        writeInt(groupStatus)
        writeString(groupName)
        writeString(swimFigure)
        writeInt(activityPoints)
        writeBoolean(isModerator)

        return getData()
    }

}