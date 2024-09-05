package com.coreduo.obbah.packet.room.unit

import com.coreduo.obbah.data.room.unit.*
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 374)
class RoomUnitPacket : HabboPacket() {
    var units: MutableList<RoomUnitData> = mutableListOf()

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        val unitsCount = readInt()
        for (i in 0 until unitsCount) {
            val pos = buffer.position()
            var unit = RoomUnitData()
            unit.deserialize(buffer)
            buffer.position(pos)

            when (unit.type) {
                1 -> {
                    unit = RoomUserData()
                    unit.deserialize(buffer)
                }
                2 -> {
                    unit = RoomPetData()
                    unit.deserialize(buffer)
                }
                3 -> {
                    unit = RoomBotData()
                    unit.deserialize(buffer)
                }
                4 -> {
                    unit = RoomRentableBotData()
                    unit.deserialize(buffer)
                }
                else -> {
                    throw Error("Unknown unit type")
                }
            }

            units.add(unit)
        }
    }

    override fun serialize(): ByteArray {
        clear()
        writeInt(units.size)
        for (unit in units) {
            when (unit.type) {
                1 -> {
                    writeBytes((unit as RoomUserData).serialize())
                }
                2 -> {
                    writeBytes((unit as RoomPetData).serialize())
                }
                3 -> {
                    writeBytes((unit as RoomBotData).serialize())
                }
                4 -> {
                    writeBytes((unit as RoomRentableBotData).serialize())
                }
                else -> {
                    throw Error("Unknown unit type")
                }
            }
        }

        return super.serialize()
    }
}