package com.coreduo.obbah.packet

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class PacketHeader(val header: Short)