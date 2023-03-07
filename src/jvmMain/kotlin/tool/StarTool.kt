package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.draw.drawPolygon
import kotlin.math.cos
import kotlin.math.sin

object StarTool : Tool("Звезда") {
    var countPeek: Int = 5
    var radius: Float = 10f
    var rotation: Float = 0f

    override fun draw(paintCanvas: PaintCanvas) {
        paintCanvas.drawPolygon(getOffsetsStar(paintCanvas.offsetRelease))
    }

    private fun getInnerOffsetsStar(list: ArrayList<Offset>): ArrayList<Offset> {

        val result = arrayListOf<Offset>()
        for (i in 0 until countPeek) {
            val (xi, yi) = list[i].x to list[i].y
            val (xi1, yi1) = list[(i + 1) % countPeek].x to list[(i + 1) % countPeek].y
            val (xi2, yi2) = list[(i + 2) % countPeek].x to list[(i + 2) % countPeek].y
            val (xi3, yi3) = list[(i + 3) % countPeek].x to list[(i + 3) % countPeek].y
            val A1 = (xi2 - xi) / (yi2 - yi)
            val A2 = (xi3 - xi1) / (yi3 - yi1)
            val y = (-xi + xi1 + A1 * yi - A2 * yi1) / (A1 - A2)
            val x = A1 * (y - yi) + xi
            result.add(Offset(x , y))
            result.add(Offset(xi2, yi2))
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
            listOffset.add(Offset( x.toFloat()+centerOffset.x,  y.toFloat()+centerOffset.y))
            current += fi
        }

        return getInnerOffsetsStar(listOffset)
    }
}