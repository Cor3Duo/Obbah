package packet.handshake

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 1488)
class UniqueMachineIDResponsePacket : HabboPacket() {
    @PacketField(order = 1)
    var machineID: String = ""
}