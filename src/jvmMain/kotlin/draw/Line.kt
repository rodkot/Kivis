package ru.nsu.ccfit.kivis.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import ru.nsu.ccfit.kivis.component.KivisImage

import java.awt.BasicStroke
import java.awt.Graphics2D
import kotlin.math.abs

fun KivisImage.drawPixel(x: Int, y: Int, color: Color) {
    if (x in 0 until width) {
        if (y in 0 until height) {
            setRGB(x, y, color.toArgb())
        }
    }
}

fun KivisImage.drawLine(offsetA: Offset, offsetB: Offset, color: Color, brush: Int) {
    when (brush) {
        1 -> {
            var (x, y) = offsetA.x.toInt() to offsetA.y.toInt()
            val (dx, dy) = (offsetB.x.toInt() - x) to (offsetB.y.toInt() - y)

            val (absx, absy) = abs(dx) to abs(dy)

            if (absx > absy) {
                var err = -absx
                for (i in 0 until absx step 1) {
                    x = if (dx > 0) x + 1 else x - 1
                    err += 2 * absy
                    if (err > 0) {
                        err -= 2 * absx
                        y = if (dy > 0) y + 1 else y - 1
                    }
                    drawPixel(x, y, color)
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
                    drawPixel(x, y, color)
                }
            }
        }

        else -> {
            val graphics: Graphics2D = createGraphics()
            graphics.color = java.awt.Color(color.toArgb())
            graphics.stroke = BasicStroke(brush.toFloat());
            graphics.drawLine(offsetA.x.toInt(), offsetA.y.toInt(), offsetB.x.toInt(), offsetB.y.toInt())
        }
    }
}