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
import ru.nsu.ccfit.kivis.component.*
import ru.nsu.ccfit.kivis.dialog.*
import ru.nsu.ccfit.kivis.draw.resize
import ru.nsu.ccfit.kivis.tool.*
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class MainWindowController {
    companion object {
        val currentTool = mutableStateOf<Tool>(PenTool)

        var size = mutableStateOf(IntSize.Zero)
        val image = mutableStateOf(KivisImage(700, 400))

        val toolBar = ToolBar(currentTool)
        val canvas = PaintCanvas()
    }
}


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
        val remImage = remember { MainWindowController.image }
        Menu.Controller.tool = MainWindowController.currentTool

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
                    .horizontalScroll(stateHorizontal)
                    .onSizeChanged {
                        if (MainWindowController.size.value == IntSize.Zero)
                            MainWindowController.image.value = MainWindowController.image.value.resize(it)
                        MainWindowController.size.value = it;

                    }
            ) {

                MainWindowController.canvas.render(remImage.value) { image: KivisImage, press: Offset, release: Offset ->
                    run {
                        remImage.value = s.draw(
                            image,
                            press,
                            release,
                            MainWindowController.size.value
                        )
                    }
                }

                if (openAction.value) {
                    MainWindowController.canvas.stop()
                    FileOpenDialog {
                        if (it != null) {
                            try {
                                val file = File(it)
                                val image = ImageIO.read(file)
                                remImage.value = image.toKivisImage()
                                openAction.value = false
                                MainWindowController.canvas.start()
                            } catch (e: Exception) {
                                openAction.value = false
                                MainWindowController.canvas.start()
                            }
                        }
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
        }
    }
}