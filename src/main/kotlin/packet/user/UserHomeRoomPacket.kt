package packet.user

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 2875)
class UserHomeRoomPacket : HabboPacket() {
    @PacketField(order = 1)
    var homeRoomId: Int = -1

    @PacketField(order = 2)
    var roomIdToEnter: Int = -1
}