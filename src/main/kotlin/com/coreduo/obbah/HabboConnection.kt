package com.coreduo.obbah

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHandler
import com.coreduo.obbah.packet.PacketHeader
import java.io.OutputStream
import java.net.Socket
import java.nio.ByteBuffer
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

class HabboConnection(host: String, port: Int) {

    private val socket: Socket
    private val output: OutputStream
    private val packetHandler: PacketHandler
    val listeners: MutableMap<KClass<out HabboPacket>, MutableList<(HabboPacket) -> Unit>> = mutableMapOf()

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

    fun sendPacket(packet: HabboPacket) {
        println("Sending packet ${packet::class.findAnnotation<PacketHeader>()?.header}")
        output.write(packet.compose())
    }

    fun sendPackets(packets: Array<HabboPacket>) {
        for (packet in packets) {
            output.write(packet.compose())
        }
    }

    inline fun <reified T : HabboPacket> listenPacket(crossinline action: (T) -> Unit) {
        var _listeners = listeners[T::class]
        if (_listeners == null) {
            _listeners = mutableListOf()
            listeners[T::class] = _listeners
        }
        _listeners.add { packet -> action(packet as T) }
    }

}