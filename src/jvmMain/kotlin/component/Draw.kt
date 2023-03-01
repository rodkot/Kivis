package ru.nsu.ccfit.kivis.component

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import kotlin.math.abs

fun Offset.righter(offsetB: Offset): Boolean {
    return x > offsetB.x
}

fun Offset.lefter(offsetB: Offset): Boolean {
    return x < offsetB.x
}

fun PaintCanvas.drawLine(offsetA: Offset, offsetB: Offset, color: Color, brush: Int) {
    val canvas = Canvas(image = image.value)
    val paint = Paint()
    paint.color = color
    when (brush) {
        1 -> {
//TODO Добавть алгоритм Брезенхема

            var (x, y) = offsetA.x.toInt() to offsetA.y.toInt()


            var (dx, dy) = (offsetB.x.toInt() - x) to (offsetB.y.toInt() - y)


            // val (dx, dy) = abs(offsetB.x.toInt() - x) to abs(offsetB.y.toInt() - y)
            var err = -dx
            for (i in 0 until abs(dx) step 1) {
                x = if (dx > 0) x + 1 else x - 1
                err += 2 * dy
                if (err > 0) {
                    err -= 2 * dx
                    y = if (dy > 0) y + 1 else y - 1
                }
                canvas.drawPoints(PointMode.Points, listOf(Offset(x.toFloat(), y.toFloat())), paint)
            }

        }

        else -> {
            paint.strokeWidth = brush.toFloat()
            canvas.drawLine(offsetA, offsetB, paint)
        }
    }
}

fun PaintCanvas.drawPolygon(offsets: List<Offset>) {
    var previous = offsets.first()
    for (offset in offsets.subList(1, offsets.size)) {
        drawLine(previous, offset, Color.Black, 1)
        previous = offset
    }
}

fun PaintCanvas.fill(offset: Offset, color: Color) {
    TODO("Реализовать")
}

fun PaintCanvas.clear() {
    val canvas = Canvas(image = image.value)
    val paint = Paint()
    paint.color = Color.White
    paint.style = PaintingStyle.Fill
    canvas.drawRect(Rect(Offset(0F, 0F), Offset(image.value.width.toFloat(), image.value.height.toFloat())), paint)
}