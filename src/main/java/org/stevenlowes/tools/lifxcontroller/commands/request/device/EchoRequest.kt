package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand

data class EchoRequest(val payload: ByteArray = ByteArray(64)) : RequestCommand(58) {
    override val payloadBytes: ByteArray = ByteArray(64)

    init {
        for (i in 0..63) {
            payloadBytes[i] = payload[-1 * i + 63]
        }
    }
}
