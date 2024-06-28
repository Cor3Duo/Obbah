package packet

import binary.Deserializer
import packet.handshake.*
import java.nio.ByteBuffer
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

class PacketHandler {

    private val packets: MutableMap<Short, KClass<out HabboPacket>> = mutableMapOf()

    init {
        registerHandshakePackets()
    }

    private fun registerHandshakePackets() {
        registerPacket(PingPacket::class)
        registerPacket(PongPacket::class)
        registerPacket(ReleaseVersionPacket::class)
        registerPacket(SecurityTicketPacket::class)
        registerPacket(UniqueMachineIDPacket::class)
    }

    private fun registerPacket(packet: KClass<out HabboPacket>) {
        val header = packet.findAnnotation<PacketHeader>()?.header
            ?: throw IllegalArgumentException("PacketHeader annotation missing")

        packets[header] = packet
    }

    fun parsePacket(data: ByteArray): HabboPacket? {
        val header = ByteBuffer.wrap(data).short
        val packet = packets[header] ?: return null

        return Deserializer.parse(data, packet)
    }

}