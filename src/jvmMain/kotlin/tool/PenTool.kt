package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.graphics.Color

class PenTool(
    val brash: Int,
    val color: Color
) : Tool() {
    companion object {
        const val name: String = "Линия"
    }
}