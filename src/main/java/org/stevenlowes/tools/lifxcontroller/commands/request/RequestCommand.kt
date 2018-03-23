package org.stevenlowes.tools.lifxcontroller.commands.request

import org.stevenlowes.tools.lifxcontroller.commands.ProtocolType
import org.stevenlowes.tools.lifxcontroller.commands.header.Frame
import org.stevenlowes.tools.lifxcontroller.commands.header.FrameAddress
import org.stevenlowes.tools.lifxcontroller.commands.header.Protocol
import org.stevenlowes.tools.lifxcontroller.values.Net
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.NetworkInterface

abstract class RequestCommand(code: Int) : ProtocolType(code), Command {
    abstract val payloadBytes: ByteArray

    private val requestBytes: ByteArray by lazy {
        val startFrame = Frame()
        val frameAddress = FrameAddress()
        val protocol = Protocol(type = code)

        val frame = Frame(size = startFrame.byteArray.size + frameAddress.byteArray.size + protocol.byteArray.size + this.payloadBytes.size)

        val byteArray = ByteArray(frame.size)
        val frameEnd = frame.byteArray.size
        val frameAddressEnd = frame.byteArray.size + frameAddress.byteArray.size
        val protocolEnd = frame.byteArray.size + frameAddress.byteArray.size + protocol.byteArray.size

        for (i in 0 until frameEnd) {
            byteArray[i] = frame.byteArray[i]
        }

        for (i in frameEnd until frameAddressEnd) {
            byteArray[i] = frameAddress.byteArray[i - frameEnd]
        }

        for (i in frameAddressEnd until protocolEnd) {
            byteArray[i] = protocol.byteArray[i - frameAddressEnd]
        }

        for (i in protocolEnd until frame.size) {
            byteArray[i] = this.payloadBytes[i - protocolEnd]
        }
        byteArray
    }

    override fun broadcast() {
        //TODO keep sendSocket open
        val socket = DatagramSocket()
        socket.broadcast = true

        val interfaces = NetworkInterface.getNetworkInterfaces()
        while (interfaces.hasMoreElements()) {
            val networkInterface = interfaces.nextElement()

            if (networkInterface.isLoopback || !networkInterface.isUp) {
                continue
            }

            for (interfaceAddress in networkInterface.interfaceAddresses) {
                val broadcast = interfaceAddress.broadcast ?: continue

                val packet = DatagramPacket(requestBytes, requestBytes.size, broadcast, Net.port)
                socket.send(packet)
            }
        }
        socket.close()
    }

    override fun send(lights: List<InetAddress>) {
        lights.map { ip ->
            DatagramPacket(requestBytes, requestBytes.size, ip, Net.port)
        }.forEach { packet ->
            Net.sendSocket.send(packet)
        }
    }
}