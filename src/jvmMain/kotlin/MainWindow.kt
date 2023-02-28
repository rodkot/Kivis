package ru.nsu.ccfit.kivis

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import ru.nsu.ccfit.kivis.component.Menu
import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.component.ToolBar
import ru.nsu.ccfit.kivis.dialog.*
import ru.nsu.ccfit.kivis.tool.*

class MainWindowController {
    companion object {
        val currentTool = mutableStateOf<Tool>(PenTool())

        val currentPenTool = mutableStateOf(PenTool())
        val currentPolygonTool = mutableStateOf(PolygonTool())
        val currentFillTool = mutableStateOf(FillTool())

        val image = mutableStateOf(ImageBitmap(10, 10))
        val counter = mutableStateOf(0L)
        var isPaint = mutableStateOf(false)

        val toolBar = ToolBar(currentTool, currentPenTool, currentFillTool, currentPolygonTool)
        val canvas = PaintCanvas(image, isPaint, counter)
    }
}


@Composable
@Preview
fun MainWindow() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val dialogAbout = remember { Menu.Controller.about }
        val dialogManual = remember { Menu.Controller.instruction }
        val dialogTool = remember { Menu.Controller.tool }

        val  imageCounter = remember { MainWindowController.counter }
        Box(Modifier.fillMaxWidth()) {
            MainWindowController.toolBar.render()
        }
        Box(Modifier.fillMaxSize().background(Color.Gray).padding(10.dp)) {
            println(imageCounter.value)
            MainWindowController.canvas.render()
        }
        when (dialogTool.value) {
            PenTool.name -> {
                PenDialog(MainWindowController.currentPenTool,
                    {
                        MainWindowController.currentTool.value = MainWindowController.currentPenTool.value
                    }) { dialogTool.value = "TOOL" }
            }

            PolygonTool.name -> {
                PolygonDialog(MainWindowController.currentPolygonTool,
                    { MainWindowController.currentTool.value = MainWindowController.currentPolygonTool.value },
                    { dialogTool.value = "TODO" })
            }

            FillTool.name -> {
                FillDialog(MainWindowController.currentFillTool,
                    { MainWindowController.currentTool.value = MainWindowController.currentFillTool.value },
                    { dialogTool.value = "TODO" })
            }

            TrashTool.name -> {
                MainWindowController.currentTool.value = TrashTool()
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