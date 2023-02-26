package ru.nsu.ccfit.kivis.tool

class PolygonTool(
    val countVertices:Int,
    val radius:Float,
    val rotation:Float
) : Tool() {
    companion object{
        const val name : String = "Многоугольник"
    }
}