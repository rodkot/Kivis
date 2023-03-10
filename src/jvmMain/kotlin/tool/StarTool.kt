package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import ru.nsu.ccfit.kivis.component.KivisImage
import ru.nsu.ccfit.kivis.draw.drawPolygon
import kotlin.math.cos
import kotlin.math.sin

object StarTool : Tool("Звезда") {
    var countPeek: Int = 5
    var radius: Float = 10f
    var rotation: Float = 0f

    override fun draw(image: KivisImage, pressOffset: Offset, releaseOffset: Offset, size: IntSize): KivisImage {
        val newImage= image.copy()
        newImage.drawPolygon(getOffsetsStar(pressOffset))
        return  newImage
    }

    private fun getInnerOffsetsStar(list: ArrayList<Offset>): ArrayList<Offset> {

        val result = arrayListOf<Offset>()
        for (i in 0 until countPeek) {
            val (xi, yi) = list[i].x to list[i].y
            val (xi1, yi1) = list[(i + 1) % countPeek].x to list[(i + 1) % countPeek].y
            val (xi2, yi2) = list[(i + 2) % countPeek].x to list[(i + 2) % countPeek].y
            val (xi3, yi3) = list[(i + 3) % countPeek].x to list[(i + 3) % countPeek].y
            try {
                val A1 = (xi2 - xi) / (yi2 - yi)
                val A2 = (xi3 - xi1) / (yi3 - yi1)
                if (A2.isInfinite()) {
                    val y = yi3
                    val x = A1 * (y - yi) + xi
                    result.add(Offset(x, y))
                    result.add(Offset(xi2, yi2))
                } else
                    if (A1.isInfinite()) {
                        val y = yi
                        val x = A2 * (y - yi1) + xi1
                        result.add(Offset(x, y))
                        result.add(Offset(xi2, yi2))
                    } else {
                        val y = (-xi + xi1 + A1 * yi - A2 * yi1) / (A1 - A2)
                        val x = A1 * (y - yi) + xi
                        result.add(Offset(x, y))
                        result.add(Offset(xi2, yi2))
                    }
            } catch (e: Exception) {
                println(e.message)
            }

        }
        return result
    }

    private fun getOffsetsStar(centerOffset: Offset): ArrayList<Offset> {
        val listOffset: ArrayList<Offset> = arrayListOf()
        val fi = 360 / countPeek
        var current = rotation

        for (i in 0 until countPeek) {
            val radians = (Math.PI / 180) * (current % 360)
            val x = radius * cos(radians)
            val y = radius * sin(radians)
            listOffset.add(Offset(x.toFloat() + centerOffset.x, y.toFloat() + centerOffset.y))
            current += fi
        }

        return getInnerOffsetsStar(listOffset)
    }
}