package com.coreduo.obbah

import com.coreduo.obbah.connection.HabboConnection
import com.coreduo.obbah.crypto.ROT13
import com.coreduo.obbah.packet.handshake.*

open class HabboCommunicator(private val connection: HabboConnection) {

    init {
        connection.listenPacket<PingPacket> {
            connection.sendPacket(PongPacket())
        }
    }

    fun sendHandshake(sso: String) {
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

    fun getHabboConnection(): HabboConnection = connection

}