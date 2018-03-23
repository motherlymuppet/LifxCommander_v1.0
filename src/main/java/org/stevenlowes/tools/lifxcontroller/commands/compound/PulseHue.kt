package org.stevenlowes.tools.lifxcontroller.commands.compound

import org.stevenlowes.tools.lifxcontroller.commands.CompoundCommand
import org.stevenlowes.tools.lifxcontroller.commands.request.light.RequestSetColor
import org.stevenlowes.tools.lifxcontroller.values.Color
import org.stevenlowes.tools.lifxcontroller.values.Hue
import org.stevenlowes.tools.lifxcontroller.values.Level

class PulseHue(hue: Hue, fadeTimeMillis: Long) : CompoundCommand(
        RequestSetColor(color = Color(hue = hue)),
        RequestSetColor(color = Color(hue = hue, brightness = Level.MIN), duration = fadeTimeMillis)
                                                                )