package ru.nsu.ccfit.kivis.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import ru.nsu.ccfit.kivis.component.PaintCanvas
import kotlin.math.abs

fun PaintCanvas.drawLine(offsetA: Offset, offsetB: Offset, color: Color, brush: Int) {
    val canvas = Canvas(image = image.value)
    val paint = Paint()
    paint.color = color
    paint.style = PaintingStyle.Fill

    when (brush) {
        1 -> {
            var (x, y) = offsetA.x.toInt() to offsetA.y.toInt()
            val (dx, dy) = (offsetB.x.toInt() - x) to (offsetB.y.toInt() - y)

            val (absx,absy) = abs(dx) to abs(dy)

            if (absx > absy) {
                var err = -absx
                for (i in 0 until absx step 1) {
                    x = if (dx > 0) x + 1 else x - 1
                    err += 2 * absy
                    if (err > 0) {
                        err -= 2 * absx
                        y = if (dy > 0) y + 1 else y - 1
                    }
                    canvas.drawRect(
                        Rect(Offset(x.toFloat(), y.toFloat()), Offset(x.toFloat() + 1, y.toFloat() + 1)),
                        paint
                    )
                }

            } else {
                var err = -absy
                for (i in 0 until absy step 1) {
                    y = if (dy > 0) y + 1 else y - 1
                    err += 2 * absx
                    if (err > 0) {
                        err -= 2 * absy
                        x = if (dx > 0) x + 1 else x - 1
                    }
                    canvas.drawRect(
                        Rect(Offset(x.toFloat(), y.toFloat()), Offset(x.toFloat() + 1, y.toFloat() + 1)),
                        paint
                    )
                }
            }
        }
        else -> {
            paint.strokeWidth = brush.toFloat()
            canvas.drawLine(offsetA, offsetB, paint)
        }
    }
}