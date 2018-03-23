package org.stevenlowes.tools.lifxcontroller.commands.request.light

import org.stevenlowes.tools.lifxcontroller.Utils
import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand
import org.stevenlowes.tools.lifxcontroller.values.Level

data class SetLightPowerRequest(val level: Level = Level.MIN, var duration: Long = 0) : RequestCommand(117) {
    override val payloadBytes: ByteArray = ByteArray(6)

    init {
        val levelBytes = level.byteArray
        payloadBytes[0] = levelBytes[0]
        payloadBytes[1] = levelBytes[1]

        val durationBytes: ByteArray = Utils.toByteArray(4, duration)
        System.arraycopy(durationBytes, 0, payloadBytes, 2, 4)
    }
}
