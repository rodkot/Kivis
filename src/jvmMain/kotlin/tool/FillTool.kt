package ru.nsu.ccfit.kivis.tool

import androidx.compose.ui.graphics.Color

class FillTool(val color: Color):Tool() {
    companion object{
        const val name : String = "Заливка"
    }
}