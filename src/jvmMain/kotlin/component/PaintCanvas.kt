package ru.nsu.ccfit.kivis.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.*


class PaintCanvas : Renderable {
    private val panel = DrawPanel()

    @Composable
    override fun render() {
        SwingPanel(
            background = Color.Gray,
            modifier = Modifier.fillMaxSize(),
            factory = {
                panel
            }
        )
    }
}
