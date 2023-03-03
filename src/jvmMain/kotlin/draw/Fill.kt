package ru.nsu.ccfit.kivis.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import ru.nsu.ccfit.kivis.component.PaintCanvas
import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

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

    if (snapshot.y < currentImage.height - 1) {
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
                snapshots.add(Snapshot(xl, xr - 1, snapshot.y + 1))
            } else {
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
                snapshots.add(Snapshot(xl, xr - 1, snapshot.y - 1))
            } else {
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
    val areaColor = pixelLine[offset.x.toInt()]
    // Пиксели которые имеют тот же цвет что и область true
    //val line = pixelLine.map { Color(it) == areaColor }
    var (xl, xr) = offset.x.toInt() to offset.x.toInt()

    while (xl >= 0 && pixelLine[xl] == areaColor) {
        xl--
    }
    while (xr < currentImage.width && pixelLine[xr] == areaColor) {
        xr++
    }
    return Snapshot(xl + 1, xr - 1, offset.y.toInt()) to Color(areaColor)
}

fun PaintCanvas.fill(offset: Offset, targetColor: Color) {
    val snapshots: Stack<Snapshot> = Stack()

    val p = findFirstSnapshot(offset)
    val areaColor = p.second
    snapshots.push(p.first)
    if (areaColor == targetColor) {
        return
    }
    while (!snapshots.empty()) {
        val currentSnapshot = snapshots.pop()
        findsSnapshot(currentSnapshot, areaColor).forEach(Consumer { snapshots.push(it) })
        drawLine(
            Offset(currentSnapshot.xl.toFloat() - 1, currentSnapshot.y.toFloat()),
            Offset(currentSnapshot.xr.toFloat(), currentSnapshot.y.toFloat()), targetColor, 1
        )
    }
}

fun PaintCanvas.clear() {
    val canvas = Canvas(image = image.value)
    val paint = Paint()
    paint.color = Color.White
    paint.style = PaintingStyle.Fill
    canvas.drawRect(Rect(Offset(0F, 0F), Offset(image.value.width.toFloat(), image.value.height.toFloat())), paint)
}