package ru.nsu.ccfit.kivis.draw

import ru.nsu.ccfit.kivis.component.PaintCanvas
import java.awt.Graphics2D
import java.awt.image.BufferedImage

fun PaintCanvas.resize() {
    val currentImage = image.value
    if (size.value.width > currentImage.width) {
        val resizeImage = BufferedImage(size.value.width, currentImage.height, BufferedImage.TYPE_INT_RGB)
        image.value = resizeImage
        clear()
        drawImage(currentImage)
    }
    if (size.value.height>currentImage.height){
        val resizeImage = BufferedImage(currentImage.width, size.value.height, BufferedImage.TYPE_INT_RGB)
        image.value = resizeImage
        clear()
        drawImage(currentImage)
    }
}

fun PaintCanvas.drawImage(bufferedImage: BufferedImage) {
    val g: Graphics2D = image.value.createGraphics()
    g.drawImage(bufferedImage, 0, 0,bufferedImage.width,bufferedImage.height, null)
}