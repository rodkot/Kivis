package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.component.drawLine

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