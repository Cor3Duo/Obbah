import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import packet.HabboPacket
import packet.PacketHandler
import packet.PacketHeader
import java.io.OutputStream
import java.net.Socket
import java.nio.ByteBuffer
import kotlin.reflect.full.findAnnotation

class HabboConnection(host: String, port: Int) {

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
                if (n < 4) break;

                val length = ByteBuffer.wrap(bytes).int
                bytes = ByteArray(length)
                n = input.read(bytes)
                if (n < length) break;

                val packet = packetHandler.parsePacket(bytes)
                if (packet != null) {
                    println(packet::class.simpleName)
                } else {
                    println("[DEBUG]: Unknown incoming packet")
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

}