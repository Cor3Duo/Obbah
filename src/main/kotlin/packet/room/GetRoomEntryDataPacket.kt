package packet.room

import packet.HabboPacket
import packet.PacketHeader

@PacketHeader(header = 2300)
class GetRoomEntryDataPacket : HabboPacket() {
}