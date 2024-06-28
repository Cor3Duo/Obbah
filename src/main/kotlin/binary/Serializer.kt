package binary

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

class Serializer {

    companion object {
        fun compose(instance: Any): ByteArray {
            val buffer = ByteBuffer.allocate(1024)

            val properties = instance::class.memberProperties
                .filter { it.findAnnotation<PacketField>() != null }
                .sortedBy { it.findAnnotation<PacketField>()?.order }

            for (property in properties) {
                when (val value = property.call(instance)) {
                    is Int -> buffer.putInt(value)
                    is Short -> buffer.putShort(value)
                    is String -> {
                        val stringBytes = value.toByteArray(UTF_8)
                        buffer.putShort(stringBytes.size.toShort())
                        buffer.put(stringBytes)
                    }

                    else -> throw IllegalArgumentException("Unsupported type")
                }
            }

            buffer.flip()
            val byteArray = ByteArray(buffer.remaining())
            buffer.get(byteArray)
            return byteArray
        }
    }
}