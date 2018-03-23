package org.stevenlowes.tools.lifxcontroller.commands.response.device

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommand

import java.math.BigInteger

data class HostFirmwareResponse(val build: BigInteger = BigInteger.ZERO,
                                val reserved: BigInteger = BigInteger.ZERO,
                                val version: Long = 0L) : ResponseCommand(15) {

    companion object {
        fun loadFrom(byteArray: ByteArray): HostFirmwareResponse {
            val buildBytes = ByteArray(8)
            for (i in 43 downTo 36) {
                buildBytes[-1 * i + 43] = byteArray[i]
            }
            val build = BigInteger(buildBytes)

            val reservedBytes = ByteArray(8)
            for (i in 51 downTo 44) {
                reservedBytes[-1 * i + 51] = byteArray[i]
            }
            val reserved = BigInteger(reservedBytes)

            var versionBinStr = ""
            for (i in 55 downTo 52) {
                versionBinStr += Utils.convertByteToBinaryString(byteArray[i])
            }
            val version = java.lang.Long.parseLong(versionBinStr, 2)

            return HostFirmwareResponse(build, reserved, version)
        }
    }

}
