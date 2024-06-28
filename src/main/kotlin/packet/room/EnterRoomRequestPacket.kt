package packet.room

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 2312)
class EnterRoomRequestPacket : HabboPacket() {
    @PacketField(order = 1)
    var roomId: Int = 0

    @PacketField(order = 2)
    var password: String = ""
}