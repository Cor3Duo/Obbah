package packet.user

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 1255)
class UserTagsPacket : HabboPacket() {
    @PacketField(order = 1)
    var roomUnitId: Int = -1

    @PacketField(order = 2)
    var tags: Array<String> = arrayOf()
}