package ru.nsu.ccfit.kivis.draw

import ru.nsu.ccfit.kivis.component.PaintCanvas
import java.awt.Graphics2D
import java.awt.image.BufferedImage

fun PaintCanvas.resize() {
    var (w, h) = size.value.width to size.value.height
    val currentImage = image.value
    if (size.value.width > currentImage.width) {
        w = size.value.width
    }
    if (size.value.height > currentImage.height) {
        h = size.value.height
    }
    val resizeImage = BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
    image.value = resizeImage
    clear()
    drawImage(currentImage)
}

fun PaintCanvas.drawImage(bufferedImage: BufferedImage) {
    val g: Graphics2D = image.value.createGraphics()
    g.drawImage(bufferedImage, 0, 0, bufferedImage.width, bufferedImage.height, null)
}