package packet.user

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 411)
class UserPermissionsPacket : HabboPacket() {
    @PacketField(order = 1)
    var clubLevel: Int = 0

    @PacketField(order = 2)
    var securityLevel: Int = 0

    @PacketField(order = 3)
    var isAmbassador: Boolean = false
}