package ru.nsu.ccfit.kivis.component

import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.IntSize
import ru.nsu.ccfit.kivis.draw.clear
import java.awt.image.BufferedImage

class KivisImage(startWidth: Int, startHeight: Int, var size: MutableState<IntSize>) :
    BufferedImage(startWidth, startHeight, TYPE_INT_RGB) {
    init {
        clear()
    }

    fun copy(): KivisImage {
        val b = KivisImage(width, height, size)
        val g = b.graphics
        g.drawImage(this, 0, 0, null)
        g.dispose()
        return b
    }

}

fun  BufferedImage.toKivisImage(size: MutableState<IntSize>):KivisImage{
    val  kivisImage = KivisImage(width,height,size)
    val g = kivisImage.graphics
    g.drawImage(this, 0, 0, null)
    g.dispose()
    return kivisImage
}