package ru.nsu.ccfit.kivis.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import ru.nsu.ccfit.kivis.button.TrashButton
import ru.nsu.ccfit.kivis.button.PenButton
import ru.nsu.ccfit.kivis.button.PolygonButton
import ru.nsu.ccfit.kivis.tool.Tool


class ToolBar : Renderable {

    companion object {
        val currentTool = mutableStateOf(Tool())
    }

    @Composable
    override fun render() {
        TopAppBar(title = {
            Row {
                Text(text = "Kivis")
            }
        }, modifier = Modifier.fillMaxWidth(),
            actions = {
                PenButton(currentTool).render()
                PolygonButton(currentTool).render()
                TrashButton(currentTool).render()
            })
    }
}