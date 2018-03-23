package org.stevenlowes.tools.lifxcontroller.commands.request.light

import org.stevenlowes.tools.lifxcontroller.commands.request.RequestCommand
import org.stevenlowes.tools.lifxcontroller.values.Level

data class RequestSetInfrared(val brightness: Level = Level.MIN) : RequestCommand(122) {
    override val payloadBytes: ByteArray = brightness.byteArray
}
