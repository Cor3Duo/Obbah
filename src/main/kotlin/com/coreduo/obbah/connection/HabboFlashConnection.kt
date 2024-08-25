package com.coreduo.obbah.connection

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHandler
import com.coreduo.obbah.packet.PacketHeader
import java.io.OutputStream
import java.net.Socket
import java.nio.ByteBuffer
import kotlin.reflect.full.findAnnotation

class HabboFlashConnection(host: String, port: Int) : HabboConnection() {

    private val socket: Socket
    private val output: OutputStream
    private val packetHandler: PacketHandler

    init {
        println("[DEBUG]: Connecting...")
        socket = Socket(host, port)
        println("[DEBUG]: Connected.")

        packetHandler = PacketHandler()
        output = socket.getOutputStream()
        val input = socket.getInputStream()

        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                var bytes = ByteArray(4)
                var n = input.read(bytes)
                if (n < 4) break

                val length = ByteBuffer.wrap(bytes).int
                bytes = ByteArray(length)
                n = input.read(bytes)
                if (n < length) break

                val packet = packetHandler.parsePacket(bytes)
                if (packet != null) {
                    val _listeners = listeners[packet::class]
                    if (_listeners != null) {
                        for (listener in _listeners) {
                            launch {
                                listener(packet)
                            }
                        }
                    }
                } else {
                    println("[DEBUG]: Unknown incoming packet {${ByteBuffer.wrap(bytes).short}}")
                }
            }
        }
    }

    override fun sendPacket(packet: HabboPacket) {
        println("Sending packet ${packet::class.findAnnotation<PacketHeader>()?.header}")
        output.write(packet.serialize())
    }

    override fun sendPackets(packets: Array<HabboPacket>) {
        for (packet in packets) {
            output.write(packet.serialize())
        }
    }

}