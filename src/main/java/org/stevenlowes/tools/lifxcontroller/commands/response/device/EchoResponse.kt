package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommand

//TODO should we really be initialising with random bytes?
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

}
