package ru.nsu.ccfit.kivis.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import ru.nsu.ccfit.kivis.component.KivisImage
import java.awt.Graphics2D
import java.util.*
import java.util.function.Consumer

data class Snapshot(
    val xl: Int,
    val xr: Int,
    val y: Int
)

fun KivisImage.findsSnapshot(snapshot: Snapshot, color: Color): List<Snapshot> {
    val snapshots: ArrayList<Snapshot> = arrayListOf()
    val pixelLine = IntArray(width)

   getRGB(0, snapshot.y, width, 1,pixelLine,0,0)
    if (Color(pixelLine[snapshot.xl]) != color) {
        return snapshots
    }

    if (snapshot.y < height - 1) {
       getRGB(0, snapshot.y+1, width, 1,pixelLine,0,0)
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
                while (current < width && pixelLine[current] == color.toArgb()) {
                    current++
                }
                val xr = current
                snapshots.add(Snapshot(xl, xr - 1, snapshot.y + 1))
            } else {
                while (current < width && pixelLine[current] != color.toArgb()) {
                    current++
                }
            }

        }
    }

    if (snapshot.y > 0) {
       getRGB(0, snapshot.y-1, width, 1,pixelLine,0,0)
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
                while (current < width && pixelLine[current] == color.toArgb()) {
                    current++
                }
                val xr = current
                snapshots.add(Snapshot(xl, xr - 1, snapshot.y - 1))
            } else {
                while (current < width && pixelLine[current] != color.toArgb()) {
                    current++
                }
            }

        }
    }
    return snapshots
}

fun KivisImage.findFirstSnapshot(offset: Offset): Pair<Snapshot, Color> {
    val pixelLine = IntArray(width)
    getRGB(0, offset.y.toInt(), width, 1,pixelLine,0,0)
    val areaColor = pixelLine[offset.x.toInt()]
    // Пиксели которые имеют тот же цвет что и область true

    var (xl, xr) = offset.x.toInt() to offset.x.toInt()

    while (xl >= 0 && pixelLine[xl] == areaColor) {
        xl--
    }
    while (xr < width && pixelLine[xr] == areaColor) {
        xr++
    }
    return Snapshot(xl + 1, xr - 1, offset.y.toInt()) to Color(areaColor)
}

fun KivisImage.fill(offset: Offset, targetColor: Color) {
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

fun KivisImage.clear() {
    val graphics: Graphics2D =  createGraphics()
    graphics.background = java.awt.Color.WHITE
    graphics.clearRect(0, 0, width, height)
    graphics.dispose()
}