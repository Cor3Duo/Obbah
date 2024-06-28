import packet.handshake.AuthenticatedPacket
import packet.room.*
import kotlin.math.floor

class HabbletBot : HabboCommunicator("proxy.habblet.city", 30000) {

    init {
        connection.listenPacket<AuthenticatedPacket> {
            connection.sendPacket(EnterRoomRequestPacket().apply {
                roomId = 6318504
            })
        }

        connection.listenPacket<EnterRoomResponsePacket> {
            connection.sendPacket(GetRoomEntryDataPacket())
        }

        connection.listenPacket<UnitChatPacket> { packet ->
            if (packet.message.startsWith(":hello")) {
                connection.sendPacket(SendRoomMessagePacket().apply {
                    message = "Hello World (${floor(Math.random() * 1000)})"
                })
            }
        }
    }

}

fun main() {
    HabbletBot()
    readLine()
}