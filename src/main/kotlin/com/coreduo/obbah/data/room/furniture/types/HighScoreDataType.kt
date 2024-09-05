package com.coreduo.obbah.data.room.furniture.types

import java.nio.ByteBuffer

class HighScoreDataType : ObjectDataBase() {

    var state: String = ""
    var scoreType: Int = -1
    var clearType: Int = -1
    var entries: MutableList<HighScoreData> = mutableListOf()

    override fun deserialize(data: ByteBuffer) {
        buffer = data

        state = readString()
        scoreType = readInt()
        clearType = readInt()

        val entriesCount = readInt()
        for (i in 0 until entriesCount) {
            val entry = HighScoreData()
            entry.score = readInt()

            val usersCount = readInt()
            for (j in 0 until usersCount) {
                entry.users.add(readString())
            }

            entries.add(entry)
        }

        super.deserialize(data)
    }

    override fun serialize(): ByteArray {
        clear()

        writeString(state)
        writeInt(scoreType)
        writeInt(clearType)
        writeInt(entries.size)
        for (entry in entries) {
            writeInt(entry.score)
            writeInt(entry.users.size)
            for (user in entry.users) {
                writeString(user)
            }
        }

        return serialize(true)
    }

    override fun getLegacyString(): String {
        return state
    }

}