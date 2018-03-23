package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommandUpdateTime

data class SetLocation(val location: ByteArray = ByteArray(16),
                       val label: String = "N/A") : RequestCommandUpdateTime(49) {
    override val payloadBytes: ByteArray = ByteArray(56)

    init {
        for (i in 0..15) {
            payloadBytes[i] = location[15 - i]
        }

        val labelBytes: ByteArray = label.toByteArray()
        for (i in 16..47) {
            payloadBytes[i] = labelBytes[47 - i]
        }

        val updatedAtBytes: ByteArray = Utils.toByteArray(8, updatedAtNanos.toLong())
        System.arraycopy(updatedAtBytes, 0, payloadBytes, 48, 8)
    }
}
