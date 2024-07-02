package com.coreduo.obbah.binary

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class PacketField(val order: Int)