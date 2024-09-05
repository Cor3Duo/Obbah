package com.coreduo.obbah.packet.inventory.achievements

import com.coreduo.obbah.data.inventory.achievements.AchievementData
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(305)
class AchievementListPacket : HabboPacket() {
    var achievements: MutableList<AchievementData> = mutableListOf()
    var defaultCategory: String = ""

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        val totalAchievements = readInt()
        for (i in 0 until totalAchievements) {
            val achievement = AchievementData()
            achievement.deserialize(buffer)
            achievements.add(achievement)
        }
        defaultCategory = readString()
    }

    override fun serialize(): ByteArray {
        clear()

        writeInt(achievements.size)
        achievements.forEach({ writeBytes(it.serialize()) })
        writeString(defaultCategory)

        return super.serialize()
    }
}