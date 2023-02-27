package ru.nsu.ccfit.kivis.tool

class PolygonTool(
    val countVertices:Int = 4,
    val radius:Float = 10f,
    val rotation:Float = 0f
) : Tool() {
    companion object{
        const val name : String = "Многоугольник"
    }
}