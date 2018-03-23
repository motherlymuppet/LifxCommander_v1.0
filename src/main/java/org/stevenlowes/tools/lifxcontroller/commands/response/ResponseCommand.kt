package org.stevenlowes.tools.lifxcontroller.commands.response

import org.stevenlowes.tools.lifxcontroller.commands.HasCode
import org.stevenlowes.tools.lifxcontroller.commands.header.Frame
import org.stevenlowes.tools.lifxcontroller.commands.header.FrameAddress
import org.stevenlowes.tools.lifxcontroller.commands.header.Protocol
import org.stevenlowes.tools.lifxcontroller.commands.response.device.*
import org.stevenlowes.tools.lifxcontroller.commands.response.light.GetInfraredResponse
import org.stevenlowes.tools.lifxcontroller.commands.response.light.LightStateResponse
import org.stevenlowes.tools.lifxcontroller.commands.response.light.LightPowerResponse

abstract class ResponseCommand(code: Int) : HasCode(code) {
    companion object {
        fun loadFrom(byteArray: ByteArray): ResponseCommand {
            val frame = Frame.loadFrom(byteArray)
            val frameAddress = FrameAddress.loadFrom(byteArray)
            val protocol = Protocol.loadFrom(byteArray)

            val payload: ResponseCommand

            when (protocol.type) {
                3 -> payload = ServiceResponse.loadFrom(byteArray)
                13 -> payload = HostInfoResponse.loadFrom(byteArray)
                15 -> payload = HostFirmwareResponse.loadFrom(byteArray)
                17 -> payload = WifiInfoResponse.loadFrom(byteArray)
                19 -> payload = WifiFirmwareResponse.loadFrom(byteArray)
                22 -> payload = GetDevicePowerResponse.loadFrom(byteArray)
                25 -> payload = GetLabelResponse.loadFrom(byteArray)
                33 -> payload = VersionResponse.loadFrom(byteArray)
                35 -> payload = InfoResponse.loadFrom(byteArray)
                45 -> payload = AckResponse()
                50 -> payload = GetLocationResponse.loadFrom(byteArray)
                53 -> payload = GetGroupResponse.loadFrom(byteArray)
                59 -> payload = EchoResponse.loadFrom(byteArray)
                107 -> payload = LightStateResponse.loadFrom(byteArray)
                118 -> payload = LightPowerResponse.loadFrom(byteArray)
                121 -> payload = GetInfraredResponse.loadFrom(byteArray)
                else -> throw RuntimeException("Code not recgonised")
            }

            return payload
        }
    }
}