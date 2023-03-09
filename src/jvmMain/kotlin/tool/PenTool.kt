package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import ru.nsu.ccfit.kivis.component.KivisImage
import ru.nsu.ccfit.kivis.draw.drawLine

object PenTool : Tool("Линия") {
    var brash: Int = 1
    var color: Color = Color.Black

    override fun draw(image: KivisImage, pressOffset: Offset, releaseOffset: Offset): KivisImage {
        val newImage= image.copy()
        newImage.drawLine(pressOffset, releaseOffset, color, brash)
        return  newImage
    }

}