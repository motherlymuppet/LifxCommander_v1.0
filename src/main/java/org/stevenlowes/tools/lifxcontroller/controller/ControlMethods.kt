package org.stevenlowes.tools.lifxcontroller.controller

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.NetworkInterface
internal object ControlMethods {
    fun receiveUdpMessage(port: Int): ByteArray {
        val socket = DatagramSocket(port)
        val data = ByteArray(1500)

        val packet = DatagramPacket(data, data.size)
        socket.receive(packet)
        socket.close()
        return packet.data
    }
}
