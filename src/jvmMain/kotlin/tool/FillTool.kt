package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import ru.nsu.ccfit.kivis.component.KivisImage
import ru.nsu.ccfit.kivis.draw.fill

object FillTool : Tool("Заливка") {
    var color: Color = Color.Red

    override fun draw(image: KivisImage, pressOffset: Offset, releaseOffset: Offset): KivisImage {
        val newImage= image.copy()
        newImage.fill(pressOffset, color)
        return  newImage

    }
}