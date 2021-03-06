package org.stevenlowes.tools.lifxcontroller.commands.response.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommand

data class GetInfraredResponse(val brightness: Int = 0) : ResponseCommand(121) {
    companion object {
        fun loadFrom(byteArray: ByteArray): GetInfraredResponse {
            val brightnessBinStr = Utils.convertByteToBinaryString(byteArray[36])
            val brightness = Integer.parseInt(brightnessBinStr, 2)
            return GetInfraredResponse(brightness)
        }
    }
}
