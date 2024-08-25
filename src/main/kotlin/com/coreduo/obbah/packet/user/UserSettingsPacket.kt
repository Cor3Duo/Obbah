package com.coreduo.obbah.packet.user

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHeader

@PacketHeader(header = 513)
class UserSettingsPacket : HabboPacket() {
    var volumeSystem: Int = 0
    var volumeFurni: Int = 0
    var volumeTrax: Int = 0
    var oldChat: Boolean = false
    var roomInvites: Boolean = false
    var cameraFollow: Boolean = false
    var flags: Int = 0
    var chatType: Int = 0

    override fun deserialize(data: ByteArray) {
        super.deserialize(data)

        volumeSystem = readInt()
        volumeFurni = readInt()
        volumeTrax = readInt()
        oldChat = readBoolean()
        roomInvites = readBoolean()
        cameraFollow = readBoolean()
        flags = readInt()
        chatType = readInt()
    }

    override fun serialize(): ByteArray {
        clear()
        writeInt(volumeSystem)
        writeInt(volumeFurni)
        writeInt(volumeTrax)
        writeBoolean(oldChat)
        writeBoolean(roomInvites)
        writeBoolean(cameraFollow)
        writeInt(flags)
        writeInt(chatType)

        return super.serialize()
    }
}