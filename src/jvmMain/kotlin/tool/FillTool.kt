package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.graphics.Color
import ru.nsu.ccfit.kivis.component.PaintCanvas

class FillTool(val color: Color = Color.Red) : Tool() {
    companion object {
        const val name: String = "Заливка"
    }

    override fun draw(paintCanvas: PaintCanvas) {
        TODO("Not yet implemented")
    }
}