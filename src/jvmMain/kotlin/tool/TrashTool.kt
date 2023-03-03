package ru.nsu.ccfit.kivis.tool

import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.draw.clear

class TrashTool : Tool() {
    companion object{
        const val name : String = "Стреть"
    }

    override fun draw(paintCanvas: PaintCanvas) {
        paintCanvas.clear()
    }
}