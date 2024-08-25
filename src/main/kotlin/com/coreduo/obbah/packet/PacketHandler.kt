package com.coreduo.obbah.packet

import com.coreduo.obbah.packet.handshake.*
import com.coreduo.obbah.packet.room.*
import com.coreduo.obbah.packet.room.access.EnterRoomRequestPacket
import com.coreduo.obbah.packet.room.access.EnterRoomResponsePacket
import com.coreduo.obbah.packet.room.access.GetRoomEntryDataPacket
import com.coreduo.obbah.packet.room.unit.RoomUnitInfoPacket
import com.coreduo.obbah.packet.room.unit.RoomUnitPacket
import com.coreduo.obbah.packet.room.unit.UnitChatPacket
import com.coreduo.obbah.packet.user.*
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
        registerPacket(RoomWalkRequestPacket::class)

        // SERVER
        registerPacket(EnterRoomResponsePacket::class)
        registerPacket(RoomUnitInfoPacket::class)
        registerPacket(UnitChatPacket::class)
        registerPacket(RoomUnitPacket::class)
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
        val packetClass = packets[header] ?: return null

        val packetInstance = packetClass.java.getDeclaredConstructor().newInstance()

        val arrayBuffer = data.copyOfRange(2, data.size)

        packetInstance.deserialize(arrayBuffer)

        return packetInstance
    }

}