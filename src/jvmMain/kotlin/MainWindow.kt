package ru.nsu.ccfit.kivis

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.nsu.ccfit.kivis.component.Menu
import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.component.ToolBar
import ru.nsu.ccfit.kivis.dialog.*
import ru.nsu.ccfit.kivis.tool.FillTool
import ru.nsu.ccfit.kivis.tool.PenTool
import ru.nsu.ccfit.kivis.tool.PolygonTool
import ru.nsu.ccfit.kivis.tool.TrashTool

@Composable
@Preview
fun MainWindow() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val dialogAbout = remember { Menu.Controller.about }
        val dialogManual = remember { Menu.Controller.instruction }
        val dialogTool = remember { Menu.Controller.tool }

        Box(Modifier.fillMaxWidth()) {
            ToolBar().render()
        }
        Box(Modifier.fillMaxSize().background(Color.Gray).padding(10.dp)) {
            PaintCanvas().render()
        }
        when(dialogTool.value){
            PenTool.name -> {
                PenDialog({ToolBar.currentTool.value = it},{dialogTool.value = "TOOL"})
            }
            PolygonTool.name ->{
                PolygonDialog({ToolBar.currentTool.value = it},{dialogTool.value = "TODO"})
            }
            FillTool.name ->{
                FillDialog({ToolBar.currentTool.value = it},{dialogTool.value = "TODO"})
            }
            TrashTool.name->{
                ToolBar.currentTool.value = TrashTool()
            }
        }

        if (dialogAbout.value) {
            AboutDialog { dialogAbout.value = false }
        }

        if (dialogManual.value) {
            ManualDialog { dialogManual.value = false }
        }
    }
}