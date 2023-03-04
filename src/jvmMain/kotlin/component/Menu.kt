package ru.nsu.ccfit.kivis.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import ru.nsu.ccfit.kivis.tool.*

class Menu() {

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun render(frameWindowScope: FrameWindowScope) {
       // var isSubmenuShowing = remember { mutableStateOf(false) }
        frameWindowScope.MenuBar {
            Menu("Файл", mnemonic = 'F') {
                Item(
                    "Сохранить",
                    onClick = { Controller.save.value = true },
                    shortcut = KeyShortcut(Key.S, ctrl = true)
                )
            }
            Menu("Инструменты", mnemonic = 'I') {
                CheckboxItem(
                    PenTool.name,
                    checked = Controller.tool.value is PenTool,
                    onCheckedChange = {
                        Controller.tool.value = PenTool
                    }
                )
                CheckboxItem(
                    PolygonTool.name,
                    checked = Controller.tool.value is PolygonTool,
                    onCheckedChange = {
                        Controller.tool.value = PolygonTool
                    }
                )
                CheckboxItem(
                    FillTool.name,
                    checked = Controller.tool.value is FillTool,
                    onCheckedChange = {
                        Controller.tool.value = FillTool
                    }
                )
                CheckboxItem(
                    TrashTool.name,
                    checked = Controller.tool.value is TrashTool,
                    onCheckedChange = {
                        Controller.tool.value = TrashTool
                    }
                )
                CheckboxItem(
                    ExpansionTool.name,
                    checked = Controller.tool.value is ExpansionTool,
                    onCheckedChange = {
                        Controller.tool.value = ExpansionTool
                    }
                )
            }
            Menu("Справка", mnemonic = 'I') {
                Item("Инструкция", onClick = { Controller.instruction.value = true })
                Item("О программе", onClick = { Controller.about.value = true })
            }
        }
    }

    object Controller {
        var save = mutableStateOf(false)
        var tool = mutableStateOf<Tool>(PenTool)
        var instruction = mutableStateOf(false)
        var about = mutableStateOf(false)
    }
}
