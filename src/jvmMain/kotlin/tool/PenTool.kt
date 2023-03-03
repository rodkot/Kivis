package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.graphics.Color
import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.draw.drawLine

class PenTool(
    val brash: Int = 1,
    val color: Color = Color.Black
) : Tool() {
    companion object {
        const val name: String = "Линия"
    }

    override fun draw(paintCanvas: PaintCanvas) {
        paintCanvas.drawLine(paintCanvas.offsetPress, paintCanvas.offsetRelease, color, brash)
    }

}