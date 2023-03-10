package ru.nsu.ccfit.kivis.component

import ru.nsu.ccfit.kivis.draw.clear
import java.awt.image.BufferedImage

class KivisImage(startWidth: Int, startHeight: Int) :
    BufferedImage(startWidth, startHeight, TYPE_INT_RGB) {
    init {
        clear()
    }

    fun copy(): KivisImage {
        val b = KivisImage(width, height)
        val g = b.graphics
        g.drawImage(this, 0, 0, null)
        g.dispose()
        return b
    }
}

fun BufferedImage.toKivisImage(): KivisImage {
    val kivisImage = KivisImage(width, height)
    val g = kivisImage.graphics
    g.drawImage(this, 0, 0, null)
    g.dispose()
    return kivisImage
}