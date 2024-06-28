package packet.handshake

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 2490)
class UniqueMachineIDPacket : HabboPacket() {
    @PacketField(order = 1)
    var machineId: String = ""

    @PacketField(order = 2)
    var fingerprint: String = ""
}