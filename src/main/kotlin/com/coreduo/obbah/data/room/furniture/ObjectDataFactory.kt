package com.coreduo.obbah.data.room.furniture

import com.coreduo.obbah.data.room.furniture.types.*

class ObjectDataFactory {

    companion object {
        fun getDataType(flags: Int): ObjectDataBase? {
            val data = when (flags and 0xFF) {
                0 -> LegacyDataType()
                1 -> MapDataType()
                2 -> StringDataType()
                3 -> VoteDataType()
                4 -> EmptyDataType()
                5 -> NumberDataType()
                6 -> HighScoreDataType()
                7 -> CrackableDataType()
                else -> return null
            }

            data.flags = flags and 0x00FF

            return data
        }
    }
}