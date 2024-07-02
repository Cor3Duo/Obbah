package com.coreduo.obbah.connection

import com.coreduo.obbah.packet.HabboPacket
import kotlin.reflect.KClass

abstract class HabboConnection {

    val listeners: MutableMap<KClass<out HabboPacket>, MutableList<(HabboPacket) -> Unit>> = mutableMapOf()

    abstract fun sendPacket(packet: HabboPacket)
    abstract fun sendPackets(packets: Array<HabboPacket>)

    inline fun <reified T : HabboPacket> listenPacket(crossinline action: (T) -> Unit) {
        var _listeners = listeners[T::class]
        if (_listeners == null) {
            _listeners = mutableListOf()
            listeners[T::class] = _listeners
        }
        _listeners.add { packet -> action(packet as T) }
    }
}