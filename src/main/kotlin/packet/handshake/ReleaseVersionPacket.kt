package packet.handshake

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 4000)
class ReleaseVersionPacket : HabboPacket() {
    @PacketField(order = 1)
    var releaseVersion: String = ""

    @PacketField(order = 2)
    var type: String = ""

    @PacketField(order = 3)
    var platform: Int = 0

    @PacketField(order = 4)
    var category: Int = 0
}