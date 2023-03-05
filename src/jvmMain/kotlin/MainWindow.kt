package ru.nsu.ccfit.kivis

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import ru.nsu.ccfit.kivis.component.Menu
import ru.nsu.ccfit.kivis.component.PaintCanvas
import ru.nsu.ccfit.kivis.component.ToolBar
import ru.nsu.ccfit.kivis.dialog.*
import ru.nsu.ccfit.kivis.tool.*
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class MainWindowController {
    companion object {
        val currentTool = mutableStateOf<Tool>(PenTool)

        val image = mutableStateOf(BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB))
        var size = mutableStateOf(IntSize.Zero)
        var isPaint = mutableStateOf(false)

        val toolBar = ToolBar(currentTool)
        val canvas = PaintCanvas(image, size, isPaint)
    }
}

var previousClick: Pair<Offset, Offset> = Offset(0f, 0f) to Offset(0f, 0f)

@Composable
@Preview
fun MainWindow() {

    val s = MainWindowController.currentTool.value
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val dialogAbout = remember { Menu.Controller.about }
        val saveAction = remember { Menu.Controller.save }
        val openAction = remember { Menu.Controller.open }
        val dialogManual = remember { Menu.Controller.instruction }
        Menu.Controller.tool = MainWindowController.currentTool

        val click = remember { MainWindowController.canvas.offsetClick }
        Box(Modifier.fillMaxWidth()) {
            MainWindowController.toolBar.render()
        }

        Box(Modifier.fillMaxSize().background(Color.Gray)) {
            val stateVertical = rememberScrollState(0)
            val stateHorizontal = rememberScrollState(0)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(stateVertical)
                    // .padding(end = 12.dp, bottom = 12.dp)
                    .horizontalScroll(stateHorizontal)
                    .onSizeChanged { MainWindowController.size.value = it }
            ) {
                if (click.value != previousClick && !openAction.value && !saveAction.value)
                    s.draw(MainWindowController.canvas)
                previousClick = click.value

                MainWindowController.canvas.render()
            }

            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd)
                    .fillMaxHeight(),
                adapter = rememberScrollbarAdapter(stateVertical),
                style = ru.nsu.ccfit.kivis.component.defaultScrollbarStyle()
            )
            HorizontalScrollbar(
                modifier = Modifier.align(Alignment.BottomStart)
                    .fillMaxWidth(),
                style = ru.nsu.ccfit.kivis.component.defaultScrollbarStyle(),
                adapter = rememberScrollbarAdapter(stateHorizontal)
            )

            if (openAction.value) {
                FileOpenDialog {
                    if (it != null) {
                        try {
                            val file = File(it)
                            val image = ImageIO.read(file)
                            MainWindowController.image.value = image
                        } catch (e: Exception) {
                            openAction.value = false
                        }
                    }
                    openAction.value = false
                }
            }

            if (saveAction.value) {
                FileSaveDialog {
                    if (it != null) {
                        val output = File(it.plus(".png"))
                        output.createNewFile()

                        try {
                            ImageIO.write(MainWindowController.image.value, "PNG", output)
                        } catch (e: IOException) {
                            println(e.message)
                        }
                    }
                    saveAction.value = false
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
}