import crypto.ROT13
import packet.handshake.*
import packet.room.EnterRoomPacket
import packet.user.UserTagsPacket

class HabboCommunicator(host: String, port: Int) {

    private val connection: HabboConnection

    init {
        connection = HabboConnection(host, port)

        connection.listenPacket<PingPacket> {
            connection.sendPacket(PongPacket())
        }

        connection.listenPacket<AuthenticatedPacket> {
            connection.sendPacket(EnterRoomPacket().apply {
                roomId = 6318504
            })
        }

        sendHandshake("ffa01e9ff9c7edc08523d62de7e2c9d9d9792f3a-41591da0ca081e73c832bc97fec1b108")
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