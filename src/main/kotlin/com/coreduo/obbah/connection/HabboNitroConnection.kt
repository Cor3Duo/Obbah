package com.coreduo.obbah.connection

import com.coreduo.obbah.packet.HabboPacket
import com.coreduo.obbah.packet.PacketHandler
import com.coreduo.obbah.packet.PacketHeader
import com.coreduo.obbah.utils.createUnsafeOkHttpClient
import kotlinx.coroutines.*
import okhttp3.*
import okio.ByteString
import okio.ByteString.Companion.toByteString
import java.nio.ByteBuffer
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.full.findAnnotation

class HabboNitroConnection(url: String, origin: String) : HabboConnection() {

    private val socket: WebSocket

    private val packetHandler = PacketHandler()
    private val listenerScope = CoroutineScope(Dispatchers.IO)

    init {
        val client = createUnsafeOkHttpClient()

        val request = Request.Builder()
            .url(url)
            .header("Origin", origin)
            .header(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36"
            )
            .build()

        println("[DEBUG]: Connecting...")
        socket = runBlocking { connectSynchronously(client, request) }
        println("[DEBUG]: Connected.")
    }

    private suspend fun connectSynchronously(client: OkHttpClient, request: Request): WebSocket =
        withContext(Dispatchers.IO) {
            suspendCoroutine { continuation ->
                var resumed = false

                val listener = object : WebSocketListener() {
                    override fun onOpen(webSocket: WebSocket, response: Response) {
                        if (!resumed) {
                            resumed = true
                            continuation.resume(webSocket)
                        }
                    }

                    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                        throw t
//                        if (!resumed) {
//                            resumed = true
//                            continuation.resumeWithException(t)
//                        }
                    }

                    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                        val data = bytes.toByteArray()
                        val buffer = ByteBuffer.wrap(data)
                        val length = buffer.int
                        if (data.size < length + 4) throw Exception("Data received has ${data.size} but length of packet is $length")

                        val sliced = ByteArray(length)
                        buffer.get(sliced)

                        val packet = packetHandler.parsePacket(sliced)
                        if (packet != null) {
                            val _listeners = listeners[packet::class]
                            if (_listeners != null) {
                                for (listener in _listeners) {
                                    listenerScope.launch {
                                        listener(packet)
                                    }
                                }
                            }
                        } else {
                            println("[DEBUG]: Unknown incoming packet {${ByteBuffer.wrap(sliced).short}}")
                        }
                    }

                    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                        println("Conexão fechando: $code / $reason")
                        webSocket.close(1000, null)
                    }

                    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                        println("Conexão fechada: $code / $reason")
                    }
                }
                client.newWebSocket(request, listener)
            }
        }

    override fun sendPacket(packet: HabboPacket) {
        println("Sending packet ${packet::class.findAnnotation<PacketHeader>()?.header}")
        socket.send(packet.serialize().toByteString())
    }

    override fun sendPackets(packets: Array<HabboPacket>) {
        for (packet in packets) {
            socket.send(packet.serialize().toByteString())
        }
    }

}