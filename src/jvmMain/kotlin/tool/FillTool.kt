package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.graphics.Color
import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.draw.fill

object FillTool : Tool("Заливка") {
    var color: Color = Color.Red

    override fun draw(paintCanvas: PaintCanvas) {
        paintCanvas.fill(paintCanvas.offsetPress, color)
    }
}