package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import ru.nsu.ccfit.kivis.component.PaintCanvas

class PenTool(
    val brash: Int = 1 ,
    val color: Color = Color.Black
) : Tool() {
    companion object {
        const val name: String = "Линия"
    }

    override fun draw(paintCanvas: PaintCanvas) {
        val canvas = Canvas(image = paintCanvas.image.value)
        val paint = Paint()
        paint.color = color
        canvas.drawLine(paintCanvas.offsetPress, paintCanvas.offsetRelease, paint)
        paintCanvas.counter.value++
    }


//    fun PaintCanvas.draw(penTool: PenTool) {
//        val canvas = Canvas(image = image.value)
//        val paint = Paint()
//        paint.color = penTool.color
//        canvas.drawLine(offsetPress, offsetRelease, paint)
//        counter.value++
//    }
}