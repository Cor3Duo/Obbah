package com.coreduo.obbah.data.unit

import com.coreduo.obbah.binary.PacketField

class RoomRentableBotData {
    @PacketField(order = 1)
    var sex: String = ""

    @PacketField(order = 2)
    var ownerId: Int = -1

    @PacketField(order = 3)
    var ownerName: String = ""

    @PacketField(order = 4)
    var skills: Array<Short> = arrayOf()
}