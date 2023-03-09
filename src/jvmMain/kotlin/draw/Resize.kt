package ru.nsu.ccfit.kivis.draw


import ru.nsu.ccfit.kivis.component.KivisImage
import java.awt.Graphics2D
import java.awt.image.BufferedImage

fun KivisImage.resize(): KivisImage {
    var (w, h) = size.value.width to size.value.height
    if (size.value.width > width) {
        w = size.value.width
    }
    if (size.value.height > height) {
        h = size.value.height
    }
    val resizeImage = KivisImage(w, h, size)
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