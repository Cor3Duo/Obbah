package com.coreduo.obbah.packet

import java.nio.ByteBuffer

open class HabboBuffer(data: ByteBuffer = ByteBuffer.allocate(1024)) {
    protected var buffer = data

    protected fun writeInt(value: Int) {
        buffer.putInt(value)
    }

    protected fun writeShort(value: Short) {
        buffer.putShort(value)
    }

    protected fun writeBoolean(value: Boolean) {
        buffer.put(if (value) 1 else 0)
    }

    protected fun writeBytes(value: ByteArray) {
        buffer.put(value)
    }

    protected fun writeString(value: String) {
        val data = value.toByteArray(Charsets.UTF_8)
        buffer.putShort(data.size.toShort())
        buffer.put(data)
    }

    protected fun readInt(): Int {
        return buffer.getInt()
    }

    protected fun readShort(): Short {
        return buffer.getShort()
    }

    protected fun readBoolean(): Boolean {
        return buffer.get() == 1.toByte()
    }

    protected fun readString(): String {
        val length = buffer.getShort()

        val data = ByteArray(length.toInt())
        buffer.get(data)

        return String(data, Charsets.UTF_8)
    }

    protected open fun clear() {
        buffer.clear()
    }

    protected fun getData(): ByteArray {
        buffer.flip()
        val byteArray = ByteArray(buffer.remaining())
        buffer.get(byteArray)

        return byteArray
    }
}