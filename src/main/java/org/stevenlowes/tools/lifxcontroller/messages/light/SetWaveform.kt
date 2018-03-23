package org.stevenlowes.tools.lifxcontroller.messages.light


import org.stevenlowes.tools.lifxcontroller.commander.CommonMethods
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.GetOnlyPayload
import org.stevenlowes.tools.lifxcontroller.messages.datatypes.HSBK

class SetWaveform(var reserved: Int = 0, var isTransient: Boolean = false, var color: HSBK = HSBK(), var period: Long = 0, var cycles: Float = 0f, var screwRatio: Int = 0, var waveform: Int = 0) :
        GetOnlyPayload(103) {

    override val byteArray: ByteArray?
        get() {
            val byteArray = ByteArray(21)

            var reservedByte: ByteArray? = ByteArray(1)
            val reservedBinStr = String.format("%8s", Integer.toBinaryString(reserved)).replace(' ', '0')
            reservedByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(reservedBinStr)
            byteArray[0] = reservedByte!![0]

            var transientByte: ByteArray? = ByteArray(1)
            val transientBinStr: String
            if (isTransient)
                transientBinStr = "00000001"
            else
                transientBinStr = "00000000"
            transientByte = CommonMethods.convertBinaryStringToLittleEndianByteArray(transientBinStr)
            byteArray[1] = transientByte!![0]

            var hueBytes: ByteArray? = ByteArray(2)
            val hueBinStr = String.format("%16s", Integer.toBinaryString(color.hue)).replace(' ', '0')
            hueBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(hueBinStr)
            byteArray[2] = hueBytes!![0]
            byteArray[3] = hueBytes[1]

            var saturationBytes: ByteArray? = ByteArray(2)
            val saturationBinStr = String.format("%16s", Integer.toBinaryString(color.saturation)).replace(' ', '0')
            saturationBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(saturationBinStr)
            byteArray[4] = saturationBytes!![0]
            byteArray[5] = saturationBytes[1]

            var brightnessBytes: ByteArray? = ByteArray(2)
            val brightnessBinStr = String.format("%16s", Integer.toBinaryString(color.brightness)).replace(' ', '0')
            brightnessBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(brightnessBinStr)
            byteArray[6] = brightnessBytes!![0]
            byteArray[7] = brightnessBytes[1]

            var kelvinBytes: ByteArray? = ByteArray(2)
            val kelvinBinStr = String.format("%16s", Integer.toBinaryString(color.kelvin)).replace(' ', '0')
            kelvinBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(kelvinBinStr)
            byteArray[8] = kelvinBytes!![0]
            byteArray[9] = kelvinBytes[1]

            var periodBytes: ByteArray? = ByteArray(4)
            val periodBinStr = String.format("%32s", java.lang.Long.toBinaryString(period)).replace(' ', '0')
            periodBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(periodBinStr)
            System.arraycopy(periodBytes!!, 0, byteArray, 10, 4)

            var cyclesBytes: ByteArray? = ByteArray(4)
            val cyclesBinStr = String.format("%32s",
                                             Integer.toBinaryString(java.lang.Float.floatToRawIntBits(cycles))).replace(
                    ' ',
                    '0')
            cyclesBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(cyclesBinStr)
            System.arraycopy(cyclesBytes!!, 0, byteArray, 14, 4)

            var scewRatioBytes: ByteArray? = ByteArray(2)
            val scewRatioBinStr = String.format("%16s", Integer.toBinaryString(screwRatio)).replace(' ', '0')
            scewRatioBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(scewRatioBinStr)
            byteArray[18] = scewRatioBytes!![0]
            byteArray[19] = scewRatioBytes[1]

            var waveformBytes: ByteArray? = ByteArray(1)
            val waveformBinStr = String.format("%8s", Integer.toBinaryString(waveform)).replace(' ', '0')
            waveformBytes = CommonMethods.convertBinaryStringToLittleEndianByteArray(waveformBinStr)
            byteArray[20] = waveformBytes!![0]

            return byteArray
        }
}