package com.coreduo.obbah.packet.room.furniture

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(1723)
class FurnitureAliasesPacket : HabboPacket() {
    var aliases: MutableMap<String, String> = mutableMapOf()

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        val count = readInt()
        for (i in 0 until count) {
            aliases[readString()] = readString()
        }
    }

    override fun serialize(): ByteArray {
        clear()
        writeInt(aliases.size)
        for ((key, value) in aliases) {
            writeString(key)
            writeString(value)
        }
        return super.serialize()
    }
}