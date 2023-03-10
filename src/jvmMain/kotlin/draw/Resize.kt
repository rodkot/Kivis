package ru.nsu.ccfit.kivis.draw


import androidx.compose.ui.unit.IntSize
import ru.nsu.ccfit.kivis.component.KivisImage
import java.awt.Graphics2D
import java.awt.image.BufferedImage

fun KivisImage.resize(size:IntSize): KivisImage {
    var (w, h) = size.width to size.height
    if (size.width > width) {
        w = size.width
    }
    if (size.height > height) {
        h = size.height
    }
    val resizeImage = KivisImage(w, h)
    //this = resizeImage
    resizeImage.clear()
    resizeImage.drawImage(this)
    return resizeImage
    //resizeImage = drawImage(this)
}

fun KivisImage.drawImage(bufferedImage: BufferedImage) {
    val g: Graphics2D = createGraphics()
    g.drawImage(bufferedImage, 0, 0, bufferedImage.width, bufferedImage.height, null)
}