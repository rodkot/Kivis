package ru.nsu.ccfit.kivis.tool

import ru.nsu.ccfit.kivis.component.PaintCanvas

abstract class Tool {
  abstract fun draw(paintCanvas: PaintCanvas)
}