package org.stevenlowes.tools.lifxcontroller.commands.request.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand
import org.stevenlowes.tools.lifxcontroller.values.Color

data class SetColor(val reserved: Int = 0, var color: Color = Color(), var duration: Long = 0) : RequestCommand(102) {
    override val payloadBytes: ByteArray = ByteArray(13)

    init {
        val reservedByte: ByteArray = Utils.toByteArray(1, reserved)
        payloadBytes[0] = reservedByte[0]

        val hueBytes: ByteArray = color.hue.byteArray
        payloadBytes[1] = hueBytes[0]
        payloadBytes[2] = hueBytes[1]

        val saturationBytes: ByteArray = color.saturation.byteArray
        payloadBytes[3] = saturationBytes[0]
        payloadBytes[4] = saturationBytes[1]

        val brightnessBytes: ByteArray = color.brightness.byteArray
        payloadBytes[5] = brightnessBytes[0]
        payloadBytes[6] = brightnessBytes[1]

        val kelvinBytes: ByteArray = color.temp.byteArray
        payloadBytes[7] = kelvinBytes[0]
        payloadBytes[8] = kelvinBytes[1]

        val durationBytes: ByteArray = Utils.toByteArray(4, duration)
        payloadBytes[9] = durationBytes[0]
        payloadBytes[10] = durationBytes[1]
        payloadBytes[11] = durationBytes[2]
        payloadBytes[12] = durationBytes[3]
    }
}
