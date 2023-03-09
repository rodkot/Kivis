package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import ru.nsu.ccfit.kivis.component.KivisImage
import ru.nsu.ccfit.kivis.draw.resize

object ExpansionTool : Tool("Растянуть") {

    override fun draw(image: KivisImage, pressOffset: Offset, releaseOffset: Offset): KivisImage {
        return image.resize()
    }
}