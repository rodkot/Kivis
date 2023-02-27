package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.graphics.Color

class PenTool(
    val brash: Int = 1 ,
    val color: Color = Color.Black
) : Tool() {
    companion object {
        const val name: String = "Линия"
    }
}