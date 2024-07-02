package com.coreduo.obbah.data.unit

import com.coreduo.obbah.binary.PacketField

class RoomUserData {
    @PacketField(order = 1)
    var sex: String = ""

    @PacketField(order = 2)
    var groupId: Int = -1

    @PacketField(order = 3)
    var groupStatus: Int = -1

    @PacketField(order = 4)
    var groupName: String = ""

    @PacketField(order = 5)
    var swimFigure: String = ""

    @PacketField(order = 6)
    var activityPoints: Int = -1

    @PacketField(order = 7)
    var isModerator: Boolean = false
}