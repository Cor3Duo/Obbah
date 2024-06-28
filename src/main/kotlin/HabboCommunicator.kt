import crypto.ROT13
import packet.outgoing.handshake.ReleaseVersionPacket
import packet.outgoing.handshake.SecurityTicketPacket
import packet.outgoing.handshake.UniqueMachineIDPacket
import java.net.Socket
import kotlinx.coroutines.*
import java.nio.ByteBuffer

class HabboCommunicator {

    private val socket: Socket

    constructor(host: String, port: Int, debug: Boolean = false) {
        if (debug) println("[DEBUG]: Connecting...")
        socket = Socket(host, port)
        if (debug) println("[DEBUG]: Connected.")

        sendHandshake("baecbb77706a1066f2beeab1674577fb2c04af53-41591da0ca081e73c832bc97fec1b108")

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

    private fun sendHandshake(sso: String) {
        val releaseVersionPacket = ReleaseVersionPacket().apply {
            releaseVersion = "PRODUCTION-202101051337-07542168"
            type = "FLASH"
            platform = 1
            category = 0
        }.compose()

        val uniqueMachineIDPacket = UniqueMachineIDPacket().apply {
            machineId = ""
            fingerprint = "TEST5-IID-1273931075-1544894443"
        }.compose()

        val securityTicketPacket = SecurityTicketPacket().apply {
            ticket = sso
            time = 9143
            encryptedTicket = ROT13.encrypt(sso)
        }.compose()

        val output = socket.getOutputStream()
        output.write(releaseVersionPacket)
        output.write(uniqueMachineIDPacket)
        output.write(securityTicketPacket)
    }

}