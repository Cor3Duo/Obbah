package com.coreduo.obbah

import com.coreduo.obbah.connection.HabboNitroConnection
import com.coreduo.obbah.packet.handshake.AuthenticatedPacket
import com.coreduo.obbah.packet.room.access.EnterRoomRequestPacket
import com.coreduo.obbah.packet.room.access.EnterRoomResponsePacket
import com.coreduo.obbah.packet.room.access.GetRoomEntryDataPacket
import java.util.concurrent.CountDownLatch

class ObbahClient : HabboCommunicator(HabboNitroConnection("wss://live-arena-d74c8s.habblet.city", "https://www.habblet.city")) {

    init {
        getHabboConnection().listenPacket<EnterRoomResponsePacket> {
            getHabboConnection().sendPacket(GetRoomEntryDataPacket())
        }

        getHabboConnection().listenPacket<AuthenticatedPacket> {
            getHabboConnection().sendPacket(EnterRoomRequestPacket().apply {
                roomId = 6383677
            })
        }

        print("SSO > ")
        sendHandshake(readln())
    }

    fun dispose() {
    }

}

fun main() {
    val latch = CountDownLatch(1)

    Thread {
        println("Pressione Ctrl+C para encerrar o programa.")
        try {
            latch.await()
        } catch (e: InterruptedException) {
            println("Programa interrompido.")
        }
    }.start()

    val bot = ObbahClient()

    Runtime.getRuntime().addShutdownHook(Thread {
        bot.dispose()
    })
}