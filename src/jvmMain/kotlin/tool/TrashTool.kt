package ru.nsu.ccfit.kivis.tool

import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.draw.clear

object TrashTool : Tool("Стреть") {

    override fun draw(paintCanvas: PaintCanvas) {
        paintCanvas.clear()
    }
}