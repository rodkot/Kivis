package ru.nsu.ccfit.kivis.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.nsu.ccfit.kivis.tool.*

class Menu() {

    @Composable
    fun render(frameWindowScope: FrameWindowScope) {
        frameWindowScope.MenuBar {
            Menu("Файл", mnemonic = 'F') {
                Item("Сохранить", onClick = { Controller.save.value = true })
            }
            Menu("Инструменты", mnemonic = 'I') {
                Item(PenTool.name, onClick = { Controller.tool.value = PenTool.name })
                Separator()
                Item(PolygonTool.name, onClick = { Controller.tool.value = PolygonTool.name })
                Separator()
                Item(TrashTool.name, onClick = { Controller.tool.value = TrashTool.name })
                Separator()
                Item(FillTool.name, onClick = { Controller.tool.value = FillTool.name })
            }
            Menu("Справка", mnemonic = 'I') {
                Item("Инструкция", onClick = { Controller.instruction.value = true })
                Item("О программе", onClick = { Controller.about.value = true })
            }
        }
    }

    object Controller {
        var save = mutableStateOf(false)
        var tool = mutableStateOf("TOOL")
        var instruction = mutableStateOf(false)
        var about = mutableStateOf(false)
    }
}
