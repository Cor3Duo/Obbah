package packet.handshake

import binary.PacketField
import packet.HabboPacket

class UniqueMachineIDHabboPacket : HabboPacket() {
    @PacketField(order = 1)
    val headerId: Short = 2490

    @PacketField(order = 2)
    var machineId: String = ""

    @PacketField(order = 3)
    var fingerprint: String = ""
}