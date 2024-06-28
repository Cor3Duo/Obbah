package packet.handshake

import binary.PacketField
import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 2596)
class PongPacket : HabboPacket() {
}