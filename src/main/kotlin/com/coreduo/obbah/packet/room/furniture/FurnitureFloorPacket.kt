package com.coreduo.obbah.packet.room.furniture

import com.coreduo.obbah.data.room.furniture.ObjectDataFactory
import com.coreduo.obbah.data.room.furniture.RoomFloorItem
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(1778)
class FurnitureFloorPacket : HabboPacket() {
    var owners: MutableMap<Int, String> = mutableMapOf()
    var items: MutableList<RoomFloorItem> = mutableListOf()

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        val ownersCount = readInt()
        for (i in 0 until ownersCount) {
            val id = readInt()
            val name = readString()
            owners[id] = name
        }

        val itemsCount = readInt()
        for (i in 0 until itemsCount) {
            val item = RoomFloorItem()
            item.deserialize(buffer)
            items.add(item)
        }

    }

    override fun serialize(): ByteArray {
        clear()

        writeInt(owners.size)
        for ((key, value) in owners) {
            writeInt(key)
            writeString(value)
        }

        writeInt(items.size)
        for (item in items) {
            buffer.put(item.serialize())
        }

        return super.serialize()
    }
}