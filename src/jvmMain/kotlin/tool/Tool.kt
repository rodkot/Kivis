package ru.nsu.ccfit.kivis.tool

import ru.nsu.ccfit.kivis.component.PaintCanvas

abstract class Tool(val name: String) {
    abstract fun draw(paintCanvas: PaintCanvas)

    fun getNameTool(): String {
        return name
    }
}