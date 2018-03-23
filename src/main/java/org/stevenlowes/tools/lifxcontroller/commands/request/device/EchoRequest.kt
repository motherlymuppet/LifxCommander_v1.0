package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand
import java.util.*

data class EchoRequest(val payload: ByteArray = ByteArray(64)) : RequestCommand(58) {
    override val payloadBytes: ByteArray = ByteArray(64)

    init {
        for (i in 0..63) {
            payloadBytes[i] = payload[-1 * i + 63]
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EchoRequest

        if (!Arrays.equals(payload, other.payload)) return false
        if (!Arrays.equals(payloadBytes, other.payloadBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(payload)
        result = 31 * result + Arrays.hashCode(payloadBytes)
        return result
    }
}
