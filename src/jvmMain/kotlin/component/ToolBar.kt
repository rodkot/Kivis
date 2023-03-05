package ru.nsu.ccfit.kivis.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import ru.nsu.ccfit.kivis.button.*
import ru.nsu.ccfit.kivis.tool.*


class ToolBar(
    private val currentTool: MutableState<Tool>
) : Renderable {

    @Composable
    override fun render() {
        TopAppBar(title = {
            Row {
                Text(currentTool.value.getNameTool())
            }
        }, modifier = Modifier.fillMaxWidth(),
            actions = {
                Row {
                    PenButton(currentTool).render()
                    PolygonButton(currentTool).render()
                    StarButton(currentTool).render()
                    FillButton(currentTool).render()
                    TrashButton(currentTool).render()
                    ExpansionButton(currentTool).render()
                }
            })
    }
}