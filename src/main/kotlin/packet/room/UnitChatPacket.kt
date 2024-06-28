package packet.room

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 1446)
class UnitChatPacket : HabboPacket() {
    @PacketField(order = 1)
    var roomIndex: Int = -1

    @PacketField(order = 2)
    var message: String = ""

    @PacketField(order = 3)
    var gesture: Int = -1

    @PacketField(order = 4)
    var bubbleId: Int = -1

    @PacketField(order = 5)
    var urls: Array<String> = arrayOf()

    @PacketField(order = 6)
    var messageLength: Int = -1
}