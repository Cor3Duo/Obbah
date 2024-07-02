package com.coreduo.obbah.crypto

class ROT13 {

    companion object {
        fun encrypt(e: String): String {
            val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
            val rot13 = "NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm"

            return e.map { char ->
                val index = alphabet.indexOf(char)
                if (index != -1) rot13[index] else char
            }.joinToString("")
        }
    }

}