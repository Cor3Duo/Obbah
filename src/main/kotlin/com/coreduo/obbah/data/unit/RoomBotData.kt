package com.coreduo.obbah.data.unit

import java.nio.ByteBuffer

class RoomBotData : RoomUnitData() {

    override fun deserialize(data: ByteBuffer) {
        super.deserialize(data)
    }

    override fun serialize(): ByteArray {
        super.serialize()
        return getData()
    }
}