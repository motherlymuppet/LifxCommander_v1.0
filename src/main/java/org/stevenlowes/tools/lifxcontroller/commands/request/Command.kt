package org.stevenlowes.tools.lifxcontroller.commands.request

import java.net.InetAddress

interface Command{
    fun send(lights: Collection<InetAddress>)

    fun send(light: InetAddress){
        send(listOf(light))
    }
}