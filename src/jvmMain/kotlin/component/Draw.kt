package ru.nsu.ccfit.kivis.component

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList
import kotlin.math.abs

fun PaintCanvas.drawLine(offsetA: Offset, offsetB: Offset, color: Color, brush: Int) {
    val canvas = Canvas(image = image.value)
    val paint = Paint()
    paint.color = color
    paint.style = PaintingStyle.Fill
    //paint.blendMode = BlendMode.Darken
    paint.alpha = 1f
    paint.strokeWidth = 1f
    when (brush) {
        1 -> {
            var (x, y) = offsetA.x.toInt() to offsetA.y.toInt()
            val (dx, dy) = (offsetB.x.toInt() - x) to (offsetB.y.toInt() - y)

            if (abs(dx) > abs(dy)) {
                var err = -abs(dx)
                for (i in 0 until abs(dx) step 1) {
                    x = if (dx > 0) x + 1 else x - 1
                    err += 2 * abs(dy)
                    if (err > 0) {
                        err -= 2 * abs(dx)
                        y = if (dy > 0) y + 1 else y - 1
                    }
                    canvas.drawRect(Rect(Offset(x.toFloat(),y.toFloat()),Offset(x.toFloat()+1,y.toFloat()+1)),paint)
                }

            } else {
                var err = -abs(dy)
                for (i in 0 until abs(dy) step 1) {
                    y = if (dy > 0) y + 1 else y - 1
                    err += 2 * abs(dx)
                    if (err > 0) {
                        err -= 2 * abs(dy)
                        x = if (dx > 0) x + 1 else x - 1
                    }
                    canvas.drawRect(Rect(Offset(x.toFloat(),y.toFloat()),Offset(x.toFloat()+1,y.toFloat()+1)),paint)
                }
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
    drawLine(previous, offsets.first(), Color.Black, 1)
}

data class Snapshot(
    val xl: Int,
    val xr: Int,
    val y: Int
)

fun PaintCanvas.findsSnapshot(snapshot: Snapshot, color: Color): List<Snapshot> {
    val snapshots: ArrayList<Snapshot> = arrayListOf()
    val currentImage = image.value
    val pixelLine = IntArray(currentImage.width)

    currentImage.readPixels(pixelLine, 0, snapshot.y, currentImage.width, 1)
    if (Color(pixelLine[snapshot.xl]) != color) {
        return snapshots
    }

    if (snapshot.y < currentImage.height-1) {
        currentImage.readPixels(pixelLine, 0, snapshot.y + 1, currentImage.width, 1)
        // Пиксели которые имеют тот же цвет что и область true
      //  val upLine = pixelLine.map { Color(it) == color }
        var current = snapshot.xl
        if (pixelLine[current] == color.toArgb()) {
            while (current > 0 && pixelLine[current] == color.toArgb()) {
                current--
            }
        }
        while (current <= snapshot.xr) {
            val xl = current
            if (pixelLine[current] == color.toArgb()) {
                while (current < currentImage.width && pixelLine[current] == color.toArgb()) {
                    current++
                }
                val xr = current
                snapshots.add(Snapshot(xl, xr-1, snapshot.y + 1))
            }
            else{
                while (current < currentImage.width && pixelLine[current] != color.toArgb()) {
                    current++
                }
            }

        }
    }

    if (snapshot.y > 0) {
        currentImage.readPixels(pixelLine, 0, snapshot.y - 1, currentImage.width, 1)
        // Пиксели которые имеют тот же цвет что и область true
        //  val upLine = pixelLine.map { Color(it) == color }
        var current = snapshot.xl
        if (pixelLine[current] == color.toArgb()) {
            while (current > 0 && pixelLine[current] == color.toArgb()) {
                current--
            }
        }
        while (current <= snapshot.xr) {
            val xl = current
            if (pixelLine[current] == color.toArgb()) {
                while (current < currentImage.width && pixelLine[current] == color.toArgb()) {
                    current++
                }
                val xr = current
                snapshots.add(Snapshot(xl, xr-1, snapshot.y - 1))
            }
            else{
                while (current < currentImage.width && pixelLine[current] != color.toArgb()) {
                    current++
                }
            }

        }
    }

    return snapshots
}

fun PaintCanvas.findFirstSnapshot(offset: Offset): Pair<Snapshot, Color> {
    val currentImage = image.value
    val pixelLine = IntArray(currentImage.width)
    currentImage.readPixels(pixelLine, 0, offset.y.toInt(), currentImage.width, 1)
    val areaColor = Color(pixelLine[offset.x.toInt()])
    // Пиксели которые имеют тот же цвет что и область true
    val line = pixelLine.map { Color(it) == areaColor }
    var (xl, xr) = offset.x.toInt() to offset.x.toInt()

    while (xl >= 0 && line[xl]) {
        xl--
    }
    while (xr < currentImage.width && line[xr]) {
        xr++
    }
    return Snapshot(xl + 1, xr - 1, offset.y.toInt()) to areaColor
}

fun PaintCanvas.fill(offset: Offset, targetColor: Color) {
    val snapshots: Stack<Snapshot> = Stack()

    val p = findFirstSnapshot(offset)
    val areaColor = p.second
    snapshots.push(p.first)

    while (!snapshots.empty()) {
        val currentSnapshot = snapshots.pop()
        findsSnapshot(currentSnapshot, areaColor).forEach(Consumer { snapshots.push(it) })
        println(snapshots.size)
        drawLine(
            Offset(currentSnapshot.xl.toFloat()-1, currentSnapshot.y.toFloat()),
            Offset(currentSnapshot.xr.toFloat(), currentSnapshot.y.toFloat()), targetColor  , 1)
    }

}

fun PaintCanvas.clear() {
    val canvas = Canvas(image = image.value)
    val paint = Paint()
    paint.color = Color.White
    paint.style = PaintingStyle.Fill
    canvas.drawRect(Rect(Offset(0F, 0F), Offset(image.value.width.toFloat(), image.value.height.toFloat())), paint)
}