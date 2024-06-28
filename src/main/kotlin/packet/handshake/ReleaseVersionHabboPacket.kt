package packet.handshake

import binary.PacketField
import packet.HabboPacket

class ReleaseVersionHabboPacket : HabboPacket() {
    @PacketField(order = 1)
    val headerId: Short = 4000

    @PacketField(order = 2)
    var releaseVersion: String = ""

    @PacketField(order = 3)
    var type: String = ""

    @PacketField(order = 4)
    var platform: Int = 0

    @PacketField(order = 5)
    var category: Int = 0
}