package com.coreduo.obbah.data.inventory.achievements

import com.coreduo.obbah.data.catalog.CatalogNodeData
import com.coreduo.obbah.packet.HabboBuffer
import java.nio.ByteBuffer

class AchievementData : HabboBuffer() {
    var achievementId: Int = -1
    var level: Int = -1
    var badgeId: String = ""
    var scoreAtStartOfLevel: Int = -1
    var scoreLimit: Int = -1
    var levelRewardPoints: Int = -1
    var levelRewardPointType: Int = -1
    var currentPoints: Int = -1
    var finalLevel: Boolean = false
    var category: String = ""
    var subCategory: String = ""
    var levelCount: Int = -1
    var displayMethod: Int = -1

    fun deserialize(data: ByteBuffer) {
        buffer = data

        achievementId = readInt()
        level = readInt()
        badgeId = readString()
        scoreAtStartOfLevel = readInt()
        scoreLimit = readInt()
        levelRewardPoints = readInt()
        levelRewardPointType = readInt()
        currentPoints = readInt()
        finalLevel = readBoolean()
        category = readString()
        subCategory = readString()
        levelCount = readInt()
        displayMethod = readInt()
    }

    fun serialize(): ByteArray {
        clear()

        writeInt(achievementId)
        writeInt(level)
        writeString(badgeId)
        writeInt(scoreAtStartOfLevel)
        writeInt(scoreLimit)
        writeInt(levelRewardPoints)
        writeInt(levelRewardPointType)
        writeInt(currentPoints)
        writeBoolean(finalLevel)
        writeString(category)
        writeString(subCategory)
        writeInt(levelCount)
        writeInt(displayMethod)

        return getData()
    }
}