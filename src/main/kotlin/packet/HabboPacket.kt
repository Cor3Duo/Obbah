package packet

import java.nio.ByteBuffer
import binary.Serializer

open class HabboPacket {

    fun compose(): ByteArray {
        var bytes = Serializer.compose(this)
        val buffer = ByteBuffer.allocate(4 + bytes.size)
        buffer.putInt(bytes.size)
        buffer.put(bytes)

        buffer.flip()
        val byteArray = ByteArray(buffer.remaining())
        buffer.get(byteArray)
        return byteArray
    }

}