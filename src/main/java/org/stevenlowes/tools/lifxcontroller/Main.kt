/*
 * Lifx Commander
 * Author: Nicholas Olsen
 * Email: olsenn@gmail.com
 * Version: v1.0
 * Date: February 08, 2018
 */

package org.stevenlowes.tools.lifxcontroller

import org.stevenlowes.tools.lifxcontroller.commands.request.light.RequestSetLightPower
import org.stevenlowes.tools.lifxcontroller.controller.ReceiveMessages
import org.stevenlowes.tools.lifxcontroller.values.Level
import java.net.InetAddress

fun main(args: Array<String>) {
    val light1 = InetAddress.getByName("192.168.1.106")
    val light2 = InetAddress.getByName("192.168.1.107")
    val light3 = InetAddress.getByName("192.168.1.108")
    val light4 = InetAddress.getByName("192.168.1.109")
    val light5 = InetAddress.getByName("192.168.1.110")
    val light6 = InetAddress.getByName("192.168.1.111")

    val closeLights = listOf(light1, light2, light3)
    val farLights = listOf(light4, light5, light6)

    val lights = listOf(light1, light2, light3, light4, light5, light6)

    // Start Receiving Incoming responses
    ReceiveMessages().start()

    val off = RequestSetLightPower(Level.MIN)
    val on = RequestSetLightPower(Level.MAX)
    val count = 1..1
    val sleep: Long = 500

    off.broadcast()

    count.forEach {
        lights.forEach { light ->
            off.broadcast()
            Thread.sleep(sleep)
            on.send(light)
            Thread.sleep(sleep)
            off.send(light)
            Thread.sleep(sleep)
        }
        //Thread.sleep(5000)
    }

    on.broadcast()
}
