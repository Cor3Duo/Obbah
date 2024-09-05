package com.coreduo.obbah.packet

import com.coreduo.obbah.packet.catalog.CatalogPurchaseGiftPacket
import com.coreduo.obbah.packet.catalog.CatalogIndexRequestPacket
import com.coreduo.obbah.packet.catalog.CatalogIndexResponsePacket
import com.coreduo.obbah.packet.handshake.*
import com.coreduo.obbah.packet.room.*
import com.coreduo.obbah.packet.room.access.RoomEntryRequestPacket
import com.coreduo.obbah.packet.room.access.RoomEntrySuccessPacket
import com.coreduo.obbah.packet.room.access.GetRoomEntryDataPacket
import com.coreduo.obbah.packet.room.access.RoomDoorbellAccessPacket
import com.coreduo.obbah.packet.room.unit.*
import com.coreduo.obbah.packet.user.*
import java.io.File
import java.nio.ByteBuffer
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

class PacketHandler {

    private val packets: MutableMap<Short, KClass<out HabboPacket>> = mutableMapOf()

    init {
//        registerHandshakePackets()
//        registerUserPackets()
//        registerRoomPackets()
//        registerCatalogPackets()
        registerPackets()
    }

    private fun registerPackets() {
        val packageName = "com.coreduo.obbah.packet"
        val classLoader = Thread.currentThread().contextClassLoader
        val path = packageName.replace('.', '/')
        val resources = classLoader.getResources(path)

        val classes = mutableListOf<Class<out HabboPacket>>()

        while (resources.hasMoreElements()) {
            val resource = resources.nextElement()
            val directory = File(resource.file)
            if (directory.exists()) {
                directory.walkTopDown().forEach { file ->
                    if (file.name.endsWith(".class")) {
                        val relativePath = file.relativeTo(directory).path
                        val className = relativePath
                            .replace(File.separatorChar, '.')
                            .removeSuffix(".class")

                        val fullClassName = "$packageName.${className.substringAfterLast(packageName)}"

                        try {
                            val clazz = Class.forName(fullClassName)
                            if (HabboPacket::class.java.isAssignableFrom(clazz) && clazz != HabboPacket::class.java) {
                                registerPacket(clazz.kotlin as KClass<out HabboPacket>)
                            }
                        } catch (e: ClassNotFoundException) {
                        }
                    }
                }
            }
        }
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
        registerPacket(RoomEntryRequestPacket::class)
        registerPacket(GetRoomEntryDataPacket::class)
        registerPacket(SendRoomMessagePacket::class)
        registerPacket(RoomWalkRequestPacket::class)
        registerPacket(RoomDoorbellAccessPacket::class)

        // SERVER
        registerPacket(RoomEntrySuccessPacket::class)
        registerPacket(RoomUnitInfoPacket::class)
        registerPacket(UnitChatPacket::class)
        registerPacket(RoomUnitPacket::class)
        registerPacket(RoomUnitRemovePacket::class)
        registerPacket(RoomUnitStatusPacket::class)
    }

    private fun registerCatalogPackets() {
        // CLIENT
        registerPacket(CatalogPurchaseGiftPacket::class)
        registerPacket(CatalogIndexRequestPacket::class)

        // SERVER
        registerPacket(CatalogIndexResponsePacket::class)
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