package org.stevenlowes.tools.lifxcontroller.commands.request.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommandUpdateTime
import java.util.*

data class SetGroupRequest(val group: ByteArray = ByteArray(16),
                           val label: String = "N/A") : RequestCommandUpdateTime(52) {

    override val payloadBytes: ByteArray = ByteArray(56)

    init {
        for (i in 0..15) {
            payloadBytes[i] = group[15 - i]
        }

        val labelBytes: ByteArray = label.toByteArray()
        for (i in 16..47) {
            payloadBytes[i] = labelBytes[47 - i]
        }

        val updatedAtBytes: ByteArray = Utils.toByteArray(8, updatedAtNanos.toLong())
        System.arraycopy(updatedAtBytes, 0, payloadBytes, 48, 8)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SetGroupRequest

        if (!Arrays.equals(group, other.group)) return false
        if (label != other.label) return false
        if (!Arrays.equals(payloadBytes, other.payloadBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(group)
        result = 31 * result + label.hashCode()
        result = 31 * result + Arrays.hashCode(payloadBytes)
        return result
    }
}
