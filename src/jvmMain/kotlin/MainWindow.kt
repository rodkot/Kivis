package ru.nsu.ccfit.kivis

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun MainWindow() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.fillMaxWidth()) {
            ToolBar().render()
        }
        Box(Modifier.fillMaxSize().background(MaterialTheme.colors.onBackground).padding(10.dp)) {
            val rem =  remember { ToolBar.currentTool }
            val d = rem.value
            PaintCanvas().render()
        }
    }
}