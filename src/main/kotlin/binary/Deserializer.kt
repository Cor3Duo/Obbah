package binary

import java.nio.ByteBuffer
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.*

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
                val value: Any = parsePrimitiveValue(buffer, property.returnType.classifier as KClass<*>)
                if (property is KMutableProperty1) {
                    property.setter.call(instance, value)
                }
            }

            return instance
        }

        private fun parsePrimitiveValue(buffer: ByteBuffer, elementType: KClass<*>): Any {
            return when {
                elementType == Int::class -> buffer.int
                elementType == Short::class -> buffer.short
                elementType == String::class -> {
                    val length = buffer.short.toInt()
                    val stringBytes = ByteArray(length)
                    buffer.get(stringBytes)
                    String(stringBytes, Charsets.UTF_8)
                }
                elementType == Boolean::class -> {
                    val byteValue = buffer.get()
                    byteValue == 1.toByte()
                }
                elementType.java.isArray -> {
                    val length = buffer.int
                    val array = java.lang.reflect.Array.newInstance(elementType.java.componentType, length) as Array<Any>

                    for (i in array.indices) {
                        array[i] = parsePrimitiveValue(buffer, elementType.java.componentType.kotlin)
                    }
                    array
                }

                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }
}
