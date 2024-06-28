package packet.handshake

import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 2491)
class AuthenticatedPacket : HabboPacket() {
}