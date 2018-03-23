package org.stevenlowes.tools.lifxcontroller.controller

import org.stevenlowes.tools.lifxcontroller.commands.response.ResponseCommand
import java.net.InetAddress

internal class ReceiveMessages(private val port: Int = 56700) : Thread() {

    override fun run() {
        println("Listening on udp: ${InetAddress.getLocalHost().hostAddress}:$port")
        while (true) {
            val byteArray = ControlMethods.receiveUdpMessage(port)
            val payload = ResponseCommand.loadFrom(byteArray)
            println(payload)
        }
    }

}
