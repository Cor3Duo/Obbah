package packet.room

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 1314)
class SendRoomMessagePacket : HabboPacket() {
    @PacketField(order = 1)
    var message: String = ""

    @PacketField(order = 2)
    var styledId: Int = 0
}