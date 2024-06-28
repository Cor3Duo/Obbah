package packet.room

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 3920)
class RoomUnitInfoPacket : HabboPacket() {
    @PacketField(order = 1)
    var unitId: Int = -1

    @PacketField(order = 2)
    var figure: String = ""

    @PacketField(order = 3)
    var gender: String = ""

    @PacketField(order = 4)
    var motto: String = ""

    @PacketField(order = 5)
    var achievementScore: Int = -1
}