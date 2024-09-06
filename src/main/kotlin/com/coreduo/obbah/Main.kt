package com.coreduo.obbah

import com.coreduo.obbah.connection.HabboNitroConnection
import com.coreduo.obbah.packet.handshake.AuthenticatedPacket
import com.coreduo.obbah.packet.inventory.achievements.AchievementListPacket
import com.coreduo.obbah.packet.room.RoomWalkRequestPacket
import com.coreduo.obbah.packet.room.SendRoomMessagePacket
import com.coreduo.obbah.packet.room.access.RoomEntryRequestPacket
import com.coreduo.obbah.packet.room.access.RoomEntrySuccessPacket
import com.coreduo.obbah.packet.room.access.GetRoomEntryDataPacket
import com.coreduo.obbah.packet.room.furniture.FurnitureFloorPacket
import com.coreduo.obbah.packet.room.unit.RoomUnitPacket
import com.coreduo.obbah.packet.room.unit.RoomUnitRemovePacket
import com.coreduo.obbah.packet.room.unit.RoomUnitStatusPacket
import com.coreduo.obbah.packet.room.unit.UnitChatPacket
import java.util.concurrent.CountDownLatch

class ObbahClient : HabboCommunicator(HabboNitroConnection("wss://live-arena-d74c8s.habblet.city", "https://www.habblet.city")) {

    init {
        var following = -1

        getHabboConnection().listenPacket<AchievementListPacket> {
            for (achievement in it.achievements) {
                println(achievement)
            }
        }

        getHabboConnection().listenPacket<RoomEntrySuccessPacket> {
            getHabboConnection().sendPacket(GetRoomEntryDataPacket())
        }

        getHabboConnection().listenPacket<AuthenticatedPacket> {
            getHabboConnection().sendPacket(RoomEntryRequestPacket().apply {
                roomId = 5407027
            })
        }

        getHabboConnection().listenPacket<RoomUnitPacket> {
//            val links = findLinks(it.units[0].custom)
//            getHabboConnection().sendPacket(SendRoomMessagePacket().apply {
//                message = "Someone entered in room."
//            })
//            if (links.isNotEmpty()) {
//                getHabboConnection().sendPacket(SendRoomMessagePacket().apply {
//                    message = "Links found: ${links.joinToString(", ")}"
//                })
//            }
        }

        getHabboConnection().listenPacket<RoomUnitRemovePacket> {
//            getHabboConnection().sendPacket(SendRoomMessagePacket().apply {
//                message = "Someone exits from room."
//            })
        }

        getHabboConnection().listenPacket<RoomUnitStatusPacket> {
            for (status in it.statuses) {
                if (status.id == following) {
                    getHabboConnection().sendPacket(RoomWalkRequestPacket().apply {
                        x = status.x
                        y = status.y
                    })
                }
            }
        }

        getHabboConnection().listenPacket<UnitChatPacket> {
            if (it.message.startsWith(":about")) {
                getHabboConnection().sendPacket(SendRoomMessagePacket().apply {
                    message = "github.com/Cor3Duo/Obbah"
                })
                getHabboConnection().sendPacket(SendRoomMessagePacket().apply {
                    message = "Open-Source bot creation tool maded by CoreDuo (x888) -> Language: Kotlin"
                })
            } else if (it.message.startsWith(":track")) {
                following = it.roomIndex
            }
        }

        print("SSO > ")
        sendHandshake(readln())
    }

    fun findLinks(text: String): List<String> {
        val regex = """(https?://)?([a-zA-Z0-9.-]+\.[a-zA-Z]{2,})(/[^\s]*)?""".toRegex()
        return regex.findAll(text).map { it.value }.toList()
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