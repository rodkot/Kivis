package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import ru.nsu.ccfit.kivis.component.KivisImage
import ru.nsu.ccfit.kivis.draw.drawPolygon
import kotlin.math.cos
import kotlin.math.sin

object PolygonTool : Tool("Многоугольник") {
    var countVertices: Int = 3
    var radius: Float = 10f
    var rotation: Float = 0f

    override fun draw(image: KivisImage, pressOffset: Offset, releaseOffset: Offset): KivisImage {
        val newImage= image.copy()
        newImage.drawPolygon(getOffsetsPolygon(pressOffset))
        return  newImage
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