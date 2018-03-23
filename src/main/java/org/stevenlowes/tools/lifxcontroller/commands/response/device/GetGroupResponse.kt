package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommandUpdateTime
import java.math.BigInteger
import java.util.*

//TODO remove default args from responses

data class GetGroupResponse(val group: ByteArray = ByteArray(16), val label: String = "N/A") : ResponseCommandUpdateTime(53) {

    companion object {
        fun loadFrom(byteArray: ByteArray): GetGroupResponse {
            val group = ByteArray(16)
            for (i in 51 downTo 36) {
                group[-1 * i + 51] = byteArray[i]
            }

            val labelBytes = ByteArray(32)
            System.arraycopy(byteArray, 52, labelBytes, 0, 32)
            val label = String(labelBytes)

            val updatedAtBytes = ByteArray(8)
            for (i in 91 downTo 84) {
                updatedAtBytes[-1 * i + 91] = byteArray[i]
            }
            val updatedAt = BigInteger(updatedAtBytes)

            val stateGroup = GetGroupResponse(group, label)
            stateGroup.updatedAt = updatedAt
            return stateGroup
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetGroupResponse

        if (!Arrays.equals(group, other.group)) return false
        if (label != other.label) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(group)
        result = 31 * result + label.hashCode()
        return result
    }
}
