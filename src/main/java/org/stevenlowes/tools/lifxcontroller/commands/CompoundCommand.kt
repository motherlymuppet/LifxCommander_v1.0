package org.stevenlowes.tools.lifxcontroller.commands

import org.stevenlowes.tools.lifxcontroller.commands.request.Command
import java.net.InetAddress

open class CompoundCommand(vararg val commands: Command): Command{
    override fun send(lights: Collection<InetAddress>) {
        for (command in commands) {
            command.send(lights)
        }
    }
}