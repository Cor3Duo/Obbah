package com.coreduo.obbah.data.room.unit

import java.nio.ByteBuffer

class RoomPetData : RoomUnitData() {
    override var type = 2
    var subType: Int = -1
    var ownerId: Int = -1
    var ownerName: String = ""
    var rarityLevel: Int = -1
    var hasSaddle: Boolean = false
    var isRiding: Boolean = false
    var canBreed: Boolean = false
    var canHarvest: Boolean = false
    var canRevive: Boolean = false
    var hasBreedingPermission: Boolean = false
    var petLevel: Int = -1
    var petPosture: String = ""

    override fun deserialize(data: ByteBuffer) {
        super.deserialize(data)

        subType = readInt()
        ownerId = readInt()
        ownerName = readString()
        rarityLevel = readInt()
        hasSaddle = readBoolean()
        isRiding = readBoolean()
        canBreed = readBoolean()
        canHarvest = readBoolean()
        canRevive = readBoolean()
        hasBreedingPermission = readBoolean()
        petLevel = readInt()
        petPosture = readString()
    }

    override fun serialize(): ByteArray {
        super.serialize()

        writeInt(subType)
        writeInt(ownerId)
        writeString(ownerName)
        writeInt(rarityLevel)
        writeBoolean(hasSaddle)
        writeBoolean(isRiding)
        writeBoolean(canBreed)
        writeBoolean(canHarvest)
        writeBoolean(canRevive)
        writeBoolean(hasBreedingPermission)
        writeInt(petLevel)
        writeString(petPosture)

        return getData()
    }
}