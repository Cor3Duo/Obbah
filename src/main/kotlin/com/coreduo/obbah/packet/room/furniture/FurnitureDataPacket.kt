package com.coreduo.obbah.packet.room.furniture

import com.coreduo.obbah.data.room.furniture.ObjectDataFactory
import com.coreduo.obbah.data.room.furniture.types.ObjectDataBase
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(2547)
class FurnitureDataPacket : HabboPacket() {
    var itemId: Int = -1
    var data: ObjectDataBase? = null

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        itemId = readInt()
        this.data = ObjectDataFactory.getDataType(readInt())

        if (this.data != null) {
            this.data!!.deserialize(buffer)
        }
    }

    override fun serialize(): ByteArray {
        clear()

        return super.serialize()
    }
}