package com.coreduo.obbah.data.room.unit

import com.coreduo.obbah.packet.HabboBuffer
import java.nio.ByteBuffer
import kotlin.math.abs

class RoomUnitStatusData : HabboBuffer() {
    var id: Int = 0
    var x: Int = 0
    var y: Int = 0
    var z: Float = 0f
    var height: Float = 0f
    var headDirection: Int = 0
    var direction: Int = 0
    var targetX: Int = 0
    var targetY: Int = 0
    var targetZ: Float = 0f
    var didMove: Boolean = false
    var canStandUp: Boolean = false
    var actions: MutableList<RoomUnitStatusAction> = mutableListOf()

    fun deserialize(data: ByteBuffer) {
        buffer = data

        id = readInt()
        x = readInt()
        y = readInt()
        z = readString().toFloat()
        headDirection = readInt()
        direction = readInt()
        val actions = readString()

        val actionParts = actions.split('/')

        for (action in actionParts) {
            val parts = action.split(' ')

            if (parts[0].isEmpty()) continue

            if (parts.size >= 2) {
                when(parts[0]) {
                    "mv" -> {
                        val values = parts[1].split(',')

                        if (values.size >= 3) {
                            targetX = values[0].toInt()
                            targetY = values[1].toInt()
                            targetZ = values[2].toFloat()
                            didMove = true
                        }
                    }
                    "sit" -> {
                        val sitHeight = parts[1].toFloat()

                        if (parts.size >= 3) canStandUp = (parts[2] == "1")

                        height = sitHeight
                    }
                    "lay" -> {
                        val layHeight = parts[1].toFloat()

                        height = abs(layHeight)
                    }
                }

                this.actions.add(RoomUnitStatusAction(parts[0], parts[1]))
            }
        }
    }

    fun serialize(): ByteArray {
        clear()



        return getData()
    }
}