package ru.nsu.ccfit.kivis.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import ru.nsu.ccfit.kivis.component.KivisImage

fun KivisImage.drawPolygon(offsets: List<Offset>) {
    var previous = offsets.first()
    for (offset in offsets.subList(1, offsets.size)) {
        drawLine(previous, offset, Color.Black, 1)
        previous = offset
    }
    drawLine(previous, offsets.first(), Color.Black, 1)
}