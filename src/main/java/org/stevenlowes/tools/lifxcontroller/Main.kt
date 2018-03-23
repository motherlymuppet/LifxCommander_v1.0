/*
 * Lifx Commander
 * Author: Nicholas Olsen
 * Email: olsenn@gmail.com
 * Version: v1.0
 * Date: February 08, 2018
 */

package org.stevenlowes.tools.lifxcontroller

import org.stevenlowes.tools.lifxcontroller.commands.request.light.RequestSetColor
import org.stevenlowes.tools.lifxcontroller.controller.ReceiveMessages
import org.stevenlowes.tools.lifxcontroller.values.Color
import org.stevenlowes.tools.lifxcontroller.values.Hue

fun main(args: Array<String>) {
    val port = 56700
    val ip1 = "192.168.1.106"
    val ip2 = "192.168.1.107"
    val ip3 = "192.168.1.108"
    val ip4 = "192.168.1.109"
    val ip5 = "192.168.1.110"
    val ip6 = "192.168.1.111"

    val ips = listOf(ip1, ip2, ip3, ip4, ip5, ip6)

    // Start Receiving Incoming commands
    ReceiveMessages(port).start()

    (1..10).forEach {
        RequestSetColor(color = Color.RANDOM).broadcast()

        Thread.sleep(200)
    }

    RequestSetColor(color = Color.WHITE).broadcast()
}
