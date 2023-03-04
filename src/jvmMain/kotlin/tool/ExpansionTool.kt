package ru.nsu.ccfit.kivis.tool

import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.draw.resize

object ExpansionTool : Tool("Растянуть") {

    override fun draw(paintCanvas: PaintCanvas) {
        paintCanvas.resize()
    }
}