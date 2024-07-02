package com.coreduo.obbah.data.unit

import com.coreduo.obbah.binary.PacketField

class RoomPetData {
    @PacketField(order = 1)
    var subType: Int = -1

    @PacketField(order = 2)
    var ownerId: Int = -1

    @PacketField(order = 3)
    var ownerName: String = ""

    @PacketField(order = 4)
    var rarityLevel: Int = -1

    @PacketField(order = 5)
    var hasSaddle: Boolean = false

    @PacketField(order = 6)
    var isRiding: Boolean = false

    @PacketField(order = 7)
    var canBreed: Boolean = false

    @PacketField(order = 8)
    var canHarvest: Boolean = false

    @PacketField(order = 9)
    var canRevive: Boolean = false

    @PacketField(order = 10)
    var hasBreedingPermission: Boolean = false

    @PacketField(order = 11)
    var petLevel: Int = -1

    @PacketField(order = 12)
    var petPosture: String = ""
}