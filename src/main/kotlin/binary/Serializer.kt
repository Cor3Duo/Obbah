package binary

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.reflect.KClass
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
                composePrimitiveType(buffer, property.call(instance))
            }

            buffer.flip()
            val byteArray = ByteArray(buffer.remaining())
            buffer.get(byteArray)
            return byteArray
        }

        private fun <T> composePrimitiveType(buffer: ByteBuffer, value: T) {
            when (value) {
                is Int -> buffer.putInt(value)
                is Short -> buffer.putShort(value)
                is String -> {
                    val stringBytes = value.toByteArray(UTF_8)
                    buffer.putShort(stringBytes.size.toShort())
                    buffer.put(stringBytes)
                }
                is Boolean -> {
                    if (value)
                        buffer.put(1)
                    else
                        buffer.put(0)
                }
                is Array<*> -> {
                    buffer.putInt(value.size)
                    value.forEach { element ->
                        composePrimitiveType(buffer, element)
                    }
                }

                else -> throw IllegalArgumentException("Unsupported type")
            }
        }

    }
}