package com.coreduo.obbah.data.unit

import com.coreduo.obbah.binary.ConditionalField
import com.coreduo.obbah.binary.PacketField

class RoomUnitData {
    @PacketField(order = 1)
    var id: Int = -1

    @PacketField(order = 2)
    var username: String = ""

    @PacketField(order = 3)
    var custom: String = ""

    @PacketField(order = 4)
    var figure: String = ""

    @PacketField(order = 5)
    var roomIndex: Int = -1

    @PacketField(order = 6)
    var x: Int = -1

    @PacketField(order = 7)
    var y: Int = -1

    @PacketField(order = 8)
    var z: String = ""

    @PacketField(order = 9)
    var direction: Int = -1

    @PacketField(order = 10)
    var type: Int = -1

    @ConditionalField(name = "type", value = "1")
    @PacketField(order = 11)
    var roomUserData: RoomUserData? = null

    @ConditionalField(name = "type", value = "2")
    @PacketField(order = 11)
    var roomPetData: RoomPetData? = null

    @ConditionalField(name = "type", value = "3")
    @PacketField(order = 11)
    var roomBotData: RoomBotData? = null

    @ConditionalField(name = "type", value = "4")
    @PacketField(order = 11)
    var roomRentableBotData: RoomRentableBotData? = null
}