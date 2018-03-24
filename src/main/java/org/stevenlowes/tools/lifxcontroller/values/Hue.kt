package org.stevenlowes.tools.lifxcontroller.values

import org.stevenlowes.tools.lifxcontroller.Utils
import java.util.*

/*
 * Constant values to be Assigned to Hue in Color Objects
 */

data class Hue(val hueDegrees: Int) {

    companion object {
        val RED = Hue(0)
        val ORANGE = Hue(90)

        private val rand = Random()
        val RANDOM: Hue get() = Hue(rand.nextInt(360))
    }

    val binaryString: String =
            String.format("%16s",
                          Integer.toBinaryString((hueDegrees.toDouble() / 360.toDouble() * Level.MAX.sixteenBitValue).toInt())).replace(
                    ' ',
                    '0')
    val byteArray: ByteArray = Utils.convertBinaryStringToLittleEndianByteArray(binaryString)
}
