package org.stevenlowes.tools.lifxcontroller.commands

import org.stevenlowes.tools.lifxcontroller.commands.request.Command
import java.net.InetAddress

open class CompoundCommand(vararg val commands: Command): Command{
    override fun send(lights: List<InetAddress>) {
        for (command in commands) {
            command.send(lights)
        }
    }

    override fun broadcast() {
        for (command in commands) {
            command.broadcast()
        }
    }
}