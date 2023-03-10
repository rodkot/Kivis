package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import ru.nsu.ccfit.kivis.component.KivisImage
import ru.nsu.ccfit.kivis.draw.clear

object TrashTool : Tool("Стреть") {

    override fun draw(image: KivisImage, pressOffset: Offset, releaseOffset: Offset, size: IntSize): KivisImage {
        val newImage= image.copy()
        newImage.clear()
        return  newImage
    }
}