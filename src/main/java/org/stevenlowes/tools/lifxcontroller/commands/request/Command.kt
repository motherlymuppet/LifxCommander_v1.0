package org.stevenlowes.tools.lifxcontroller.commands.request

import java.net.InetAddress

interface Command{
    fun send(lights: List<InetAddress>)

    fun send(light: InetAddress){
        send(listOf(light))
    }
}