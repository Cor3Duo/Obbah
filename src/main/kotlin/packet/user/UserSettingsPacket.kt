package packet.user

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 513)
class UserSettingsPacket : HabboPacket() {
    @PacketField(order = 1)
    var volumeSystem: Int = 0

    @PacketField(order = 2)
    var volumeFurni: Int = 0

    @PacketField(order = 3)
    var volumeTrax: Int = 0

    @PacketField(order = 4)
    var oldChat: Boolean = false

    @PacketField(order = 5)
    var roomInvites: Boolean = false

    @PacketField(order = 6)
    var cameraFollow: Boolean = false

    @PacketField(order = 7)
    var flags: Int = 0

    @PacketField(order = 8)
    var chatType: Int = 0
}