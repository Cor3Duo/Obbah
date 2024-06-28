import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import packet.HabboPacket
import java.io.OutputStream
import java.net.Socket
import java.nio.ByteBuffer

class HabboConnection(host: String, port: Int) {

    private val socket: Socket
    private val output: OutputStream

    init {
        println("[DEBUG]: Connecting...")
        socket = Socket(host, port)
        println("[DEBUG]: Connected.")
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

                println(n)
            }
        }
    }

    fun sendPacket(packet: HabboPacket) {
        output.write(packet.compose())
    }

    fun sendPackets(packets: Array<HabboPacket>) {
        for (packet in packets) {
            output.write(packet.compose())
        }
    }

}