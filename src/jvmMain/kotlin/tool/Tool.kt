package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import ru.nsu.ccfit.kivis.component.KivisImage

abstract class Tool(val name: String) {
    abstract fun draw(image: KivisImage, pressOffset: Offset, releaseOffset: Offset):KivisImage

    fun getNameTool(): String {
        return name
    }
}