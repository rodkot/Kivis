package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.graphics.Color
import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.draw.drawLine

object PenTool : Tool("Линия") {
    var brash: Int = 1
    var color: Color = Color.Black

    override fun draw(paintCanvas: PaintCanvas) {
        paintCanvas.drawLine(paintCanvas.offsetPress, paintCanvas.offsetRelease, color, brash)
    }

}