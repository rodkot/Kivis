package ru.nsu.ccfit.kivis.tool

import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.draw.resize

class ExpansionTool() : Tool() {
    companion object {
        const val name: String = "Растянуть"
    }

    override fun draw(paintCanvas: PaintCanvas) {
        paintCanvas.resize()
    }
}