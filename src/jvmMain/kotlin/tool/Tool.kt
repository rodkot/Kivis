package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import ru.nsu.ccfit.kivis.component.KivisImage

abstract class Tool(val name: String) {
    abstract fun draw(image: KivisImage, pressOffset: Offset, releaseOffset: Offset, size: IntSize): KivisImage

    fun getNameTool(): String {
        return name
    }
}