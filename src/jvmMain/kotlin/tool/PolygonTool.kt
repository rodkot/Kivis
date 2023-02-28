package ru.nsu.ccfit.kivis.tool

import ru.nsu.ccfit.kivis.component.PaintCanvas

class PolygonTool(
    val countVertices:Int = 4,
    val radius:Float = 10f,
    val rotation:Float = 0f
) : Tool() {
    companion object{
        const val name : String = "Многоугольник"
    }

    override fun draw(paintCanvas: PaintCanvas) {
        TODO("Not yet implemented")
    }
}