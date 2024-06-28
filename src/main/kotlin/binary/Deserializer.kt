package binary

import java.nio.ByteBuffer
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.KMutableProperty1

class Deserializer {

    companion object {
        inline fun <reified T : Any> parse(bytes: ByteArray): T {
            return parse(bytes, T::class)
        }

        fun <T : Any> parse(bytes: ByteArray, clazz: KClass<T>): T {
            val buffer = ByteBuffer.wrap(bytes)
            val instance = clazz.createInstance()

            val properties = clazz.memberProperties
                .filter { it.findAnnotation<PacketField>() != null }
                .sortedBy { it.findAnnotation<PacketField>()?.order }

            for (property in properties) {
                val value: Any = when (property.returnType.classifier) {
                    Int::class -> buffer.int
                    Short::class -> buffer.short
                    String::class -> {
                        val length = buffer.short.toInt()
                        val stringBytes = ByteArray(length)
                        buffer.get(stringBytes)
                        String(stringBytes, Charsets.UTF_8)
                    }

                    else -> throw IllegalArgumentException("Unsupported type")
                }
                if (property is KMutableProperty1) {
                    property.setter.call(instance, value)
                }
            }

            return instance
        }
    }
}
