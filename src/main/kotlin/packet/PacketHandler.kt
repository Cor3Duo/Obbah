package packet

import binary.Deserializer
import packet.handshake.*
import packet.room.*
import packet.user.*
import java.nio.ByteBuffer
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

class PacketHandler {

    private val packets: MutableMap<Short, KClass<out HabboPacket>> = mutableMapOf()

    init {
        registerHandshakePackets()
        registerUserPackets()
        registerRoomPackets()
    }

    private fun registerHandshakePackets() {
        // CLIENT
        registerPacket(PongPacket::class)
        registerPacket(ReleaseVersionPacket::class)
        registerPacket(SecurityTicketPacket::class)
        registerPacket(UniqueMachineIDPacket::class)

        // SERVER
        registerPacket(PingPacket::class)
        registerPacket(AuthenticatedPacket::class)
        registerPacket(UniqueMachineIDResponsePacket::class)
        registerPacket(AvailabilityStatusPacket::class)
    }

    private fun registerUserPackets() {
        // CLIENT

        // SERVER
        registerPacket(UserPermissionsPacket::class)
        registerPacket(UserTagsPacket::class)
        registerPacket(UserSettingsPacket::class)
        registerPacket(UserHomeRoomPacket::class)
    }

    private fun registerRoomPackets() {
        // CLIENT
        registerPacket(EnterRoomRequestPacket::class)
        registerPacket(GetRoomEntryDataPacket::class)
        registerPacket(SendRoomMessagePacket::class)

        // SERVER
        registerPacket(EnterRoomResponsePacket::class)
        registerPacket(RoomUnitInfoPacket::class)
        registerPacket(UnitChatPacket::class)
    }

    private fun registerPacket(packet: KClass<out HabboPacket>) {
        val header = packet.findAnnotation<PacketHeader>()?.header
            ?: throw IllegalArgumentException("PacketHeader annotation missing")

        packets[header] = packet
    }

    fun parsePacket(data: ByteArray): HabboPacket? {
        if (data.size < 2) {
            throw Exception("Data has less than 2 bytes")
        }

        val header = ByteBuffer.wrap(data).short
        val packet = packets[header] ?: return null

        return Deserializer.parse(data.copyOfRange(2, data.size), packet)
    }

}