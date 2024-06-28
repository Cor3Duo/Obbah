import crypto.ROT13
import packet.handshake.ReleaseVersionHabboPacket
import packet.handshake.SecurityTicketHabboPacket
import packet.handshake.UniqueMachineIDHabboPacket

class HabboCommunicator(host: String, port: Int) {

    private val connection: HabboConnection

    init {
        connection = HabboConnection(host, port)
    }

    private fun sendHandshake(sso: String) {
        val releaseVersionPacket = ReleaseVersionHabboPacket().apply {
            releaseVersion = "PRODUCTION-202101051337-07542168"
            type = "FLASH"
            platform = 1
            category = 0
        }

        val uniqueMachineIDPacket = UniqueMachineIDHabboPacket().apply {
            machineId = ""
            fingerprint = "TEST5-IID-1273931075-1544894443"
        }

        val securityTicketPacket = SecurityTicketHabboPacket().apply {
            ticket = sso
            time = 9143
            encryptedTicket = ROT13.encrypt(sso)
        }

        connection.sendPacket(releaseVersionPacket)
        connection.sendPacket(uniqueMachineIDPacket)
        connection.sendPacket(securityTicketPacket)
    }

}