package packet.handshake

import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 3928)
class PingPacket : HabboPacket() {
}