package ru.nsu.ccfit.kivis.component

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*

fun PaintCanvas.drawLine(offsetA: Offset, offsetB: Offset, color: Color, brush: Int) {
    val canvas = Canvas(image = image.value)
    val paint = Paint()
    paint.color = color

    //TODO Добавть алгоритм Брезенхема
    canvas.drawLine(offsetA, offsetB, paint)
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
    canvas.drawRect(Rect(Offset(0F, 0F), Offset(image.value.width.toFloat() ,image.value.height.toFloat())), paint)
}