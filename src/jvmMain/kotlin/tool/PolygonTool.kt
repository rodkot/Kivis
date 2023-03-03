package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.draw.drawPolygon
import kotlin.math.cos
import kotlin.math.sin

class PolygonTool(
    val countVertices: Int = 3,
    val radius: Float = 10f,
    val rotation: Float = 0f
) : Tool() {
    companion object {
        const val name: String = "Многоугольник"
    }

    override fun draw(paintCanvas: PaintCanvas) {
        paintCanvas.drawPolygon(getOffsetsPolygon(paintCanvas.offsetPress))
    }

    private fun getOffsetsPolygon(centerOffset: Offset): List<Offset> {
        val listOffset: ArrayList<Offset> = arrayListOf()
        val fi = 360 / countVertices
        var current = rotation

        for (i in 0 until countVertices) {
            val radians = (Math.PI / 180) * (current % 360)
            val x = radius * cos(radians)
            val y = radius * sin(radians)
            listOffset.add(Offset(centerOffset.x + x.toFloat(), centerOffset.y + y.toFloat()))
            current += fi
        }
        return listOffset
    }
}