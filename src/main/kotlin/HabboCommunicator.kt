import crypto.ROT13
import packet.handshake.*

open class HabboCommunicator(host: String, port: Int) {

    protected val connection: HabboConnection

    init {
        connection = HabboConnection(host, port)

        connection.listenPacket<PingPacket> {
            connection.sendPacket(PongPacket())
        }

        sendHandshake("c500ec850ca9579515b5e4469061e0b3a8d95d60-41591da0ca081e73c832bc97fec1b108")
    }

    private fun sendHandshake(sso: String) {
        val releaseVersionPacket = ReleaseVersionPacket().apply {
            releaseVersion = "PRODUCTION-202101051337-07542168"
            type = "FLASH"
            platform = 1
            category = 0
        }

        val uniqueMachineIDPacket = UniqueMachineIDPacket().apply {
            machineId = ""
            fingerprint = "TEST2-IID-2920772483"
        }

        val securityTicketPacket = SecurityTicketPacket().apply {
            ticket = sso
            time = 9143
            encryptedTicket = ROT13.encrypt(sso)
        }

        connection.sendPacket(releaseVersionPacket)
        connection.sendPacket(uniqueMachineIDPacket)
        connection.sendPacket(securityTicketPacket)
    }

}