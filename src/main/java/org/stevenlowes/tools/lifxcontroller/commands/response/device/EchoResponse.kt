package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommand
import java.util.*

data class EchoResponse(val bytes: ByteArray = ByteArray(64)) : ResponseCommand(59) {
    companion object {
        fun loadFrom(byteArray: ByteArray): EchoResponse {
            val bytes = ByteArray(64)
            for (i in 99 downTo 36) {
                bytes[-1 * i + 99] = byteArray[i]
            }
            return EchoResponse(bytes)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EchoResponse

        if (!Arrays.equals(bytes, other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(bytes)
    }

}
