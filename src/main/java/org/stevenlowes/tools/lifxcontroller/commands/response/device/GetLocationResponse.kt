package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommandUpdateTime
import java.math.BigInteger
import java.util.*

data class GetLocationResponse(val location: ByteArray = ByteArray(16),
                               val label: String = "N/A") : ResponseCommandUpdateTime(50) {

    companion object {
        fun loadFrom(byteArray: ByteArray): GetLocationResponse {
            val location = ByteArray(16)
            for (i in 51 downTo 36) {
                location[-1 * i + 51] = byteArray[i]
            }

            val labelBytes = ByteArray(32)
            System.arraycopy(byteArray, 52, labelBytes, 0, 32)
            val label = String(labelBytes)

            //String updatedAtBinStr = "";
            //for(int i=91; i>83; i--) updatedAtBinStr = updatedAtBinStr.concat(Utils.convertByteToBinaryString(payloadBytes[i]));
            //updated_at = BigInteger.valueOf(Long.parseLong(updatedAtBinStr, 2));
            val updatedAtBytes = ByteArray(8)
            for (i in 91 downTo 84) {
                updatedAtBytes[-1 * i + 91] = byteArray[i]
            }
            val updatedAt = BigInteger(updatedAtBytes)

            val stateLocation = GetLocationResponse(location, label)
            stateLocation.updatedAt = updatedAt
            return stateLocation
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetLocationResponse

        if (!Arrays.equals(location, other.location)) return false
        if (label != other.label) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(location)
        result = 31 * result + label.hashCode()
        return result
    }
}
