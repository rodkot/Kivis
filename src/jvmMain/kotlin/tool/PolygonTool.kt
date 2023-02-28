package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.component.drawPolygon

class PolygonTool(
    val countVertices: Int = 4,
    val radius: Float = 10f,
    val rotation: Float = 0f
) : Tool() {
    companion object {
        const val name: String = "Многоугольник"
    }

    override fun draw(paintCanvas: PaintCanvas) {
        paintCanvas.drawPolygon(getOffsetsPolygon())
    }

    private fun getOffsetsPolygon(): List<Offset> {
        val fi  = 360/countVertices
        val full  = fi + rotation

        TODO("Реализовать")
    }
}