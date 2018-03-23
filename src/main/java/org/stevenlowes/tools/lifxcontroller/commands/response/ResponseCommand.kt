package org.stevenlowes.tools.lifxcontroller.commands.response

import org.stevenlowes.tools.lifxcontroller.commands.ProtocolType
import org.stevenlowes.tools.lifxcontroller.commands.header.Protocol
import org.stevenlowes.tools.lifxcontroller.commands.response.device.*
import org.stevenlowes.tools.lifxcontroller.commands.response.light.GetInfraredResponse
import org.stevenlowes.tools.lifxcontroller.commands.response.light.LightStateResponse
import org.stevenlowes.tools.lifxcontroller.commands.response.light.LightPowerResponse

abstract class ResponseCommand(code: Int) : ProtocolType(code) {
    companion object {
        fun loadFrom(byteArray: ByteArray): ResponseCommand? {
            return when (Protocol.loadFrom(byteArray).type) {
                3 -> ServiceResponse.loadFrom(byteArray)
                13 -> HostInfoResponse.loadFrom(byteArray)
                15 -> HostFirmwareResponse.loadFrom(byteArray)
                17 -> WifiInfoResponse.loadFrom(byteArray)
                19 -> WifiFirmwareResponse.loadFrom(byteArray)
                22 -> GetDevicePowerResponse.loadFrom(byteArray)
                25 -> GetLabelResponse.loadFrom(byteArray)
                33 -> VersionResponse.loadFrom(byteArray)
                35 -> InfoResponse.loadFrom(byteArray)
                45 -> AckResponse()
                50 -> GetLocationResponse.loadFrom(byteArray)
                53 -> GetGroupResponse.loadFrom(byteArray)
                59 -> EchoResponse.loadFrom(byteArray)
                107 -> LightStateResponse.loadFrom(byteArray)
                118 -> LightPowerResponse.loadFrom(byteArray)
                121 -> GetInfraredResponse.loadFrom(byteArray)
                else -> null
            }
        }
    }
}