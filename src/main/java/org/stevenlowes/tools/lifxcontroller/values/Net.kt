package org.stevenlowes.tools.lifxcontroller.values

import java.net.DatagramSocket

class Net{
    companion object {
        val sendSocket = DatagramSocket()
        val port = 56700
    }
}