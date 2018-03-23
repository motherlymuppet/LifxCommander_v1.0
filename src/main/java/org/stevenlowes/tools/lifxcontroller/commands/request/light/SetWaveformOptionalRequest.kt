package org.stevenlowes.tools.lifxcontroller.commands.request.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand
import org.stevenlowes.tools.lifxcontroller.values.Color
import org.stevenlowes.tools.lifxcontroller.values.Level
import org.stevenlowes.tools.lifxcontroller.values.Waveform

data class SetWaveformOptionalRequest(val reserved: Int = 0,
                                      val isTransient: Boolean = false,
                                      val color: Color = Color(),
                                      val period: Long = 0,
                                      val cycles: Float = 0f,
                                      val skewRatio: Level = Level.MIN,
                                      val waveform: Waveform = Waveform.SAWTOOTH,
                                      val setHue: Boolean = true,
                                      val setSaturation: Boolean = true,
                                      val setBrightness: Boolean = true,
                                      val setKelvin: Boolean = true) : RequestCommand(119) {
    override val payloadBytes: ByteArray = ByteArray(25)

    init {
        val reservedByte: ByteArray = Utils.toByteArray(1, reserved)
        payloadBytes[0] = reservedByte[0]

        val transientByte: ByteArray = Utils.boolToByteArray(isTransient)
        payloadBytes[1] = transientByte[0]

        val hueBytes: ByteArray = color.hue.byteArray
        payloadBytes[2] = hueBytes[0]
        payloadBytes[3] = hueBytes[1]

        val saturationBytes: ByteArray = color.saturation.byteArray
        payloadBytes[4] = saturationBytes[0]
        payloadBytes[5] = saturationBytes[1]

        val brightnessBytes: ByteArray = color.brightness.byteArray
        payloadBytes[6] = brightnessBytes[0]
        payloadBytes[7] = brightnessBytes[1]

        val tempBytes: ByteArray = color.temp.byteArray
        payloadBytes[8] = tempBytes[0]
        payloadBytes[9] = tempBytes[1]

        val periodBytes: ByteArray = Utils.toByteArray(4, period)
        System.arraycopy(periodBytes, 0, payloadBytes, 10, 4)

        val cyclesBytes: ByteArray = Utils.toByteArray(4, cycles)
        System.arraycopy(cyclesBytes, 0, payloadBytes, 14, 4)

        val scewRatioBytes: ByteArray = skewRatio.byteArray
        payloadBytes[18] = scewRatioBytes[0]
        payloadBytes[19] = scewRatioBytes[1]

        val waveformBytes: ByteArray = waveform.byteArray
        payloadBytes[20] = waveformBytes[0]

        val setHueByte: ByteArray = Utils.boolToByteArray(setHue)
        payloadBytes[21] = setHueByte[0]

        val setSaturationByte: ByteArray = Utils.boolToByteArray(setSaturation)
        payloadBytes[22] = setSaturationByte[0]

        val setBrightnessByte: ByteArray = Utils.boolToByteArray(setBrightness)
        payloadBytes[23] = setBrightnessByte[0]

        val setKelvinByte: ByteArray = Utils.boolToByteArray(setKelvin)
        payloadBytes[24] = setKelvinByte[0]
    }
}
