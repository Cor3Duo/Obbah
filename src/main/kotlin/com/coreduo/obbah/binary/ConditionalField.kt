package com.coreduo.obbah.binary

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class ConditionalField(val name: String, val value: String)