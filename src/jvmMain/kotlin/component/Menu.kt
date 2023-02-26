package ru.nsu.ccfit.kivis.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.nsu.ccfit.kivis.tool.PenTool
import ru.nsu.ccfit.kivis.tool.PolygonTool
import ru.nsu.ccfit.kivis.tool.Tool
import ru.nsu.ccfit.kivis.tool.TrashTool

class Menu() {
    
    @Composable
    fun render(frameWindowScope: FrameWindowScope) {
        frameWindowScope.MenuBar {
            Menu("Файл", mnemonic = 'F') {
                Item("Сохранить", onClick = { Controller.save() })
            }
            Menu("Инструменты", mnemonic = 'I') {
                Item(PenTool.name, onClick = { Controller.tool(PenTool()) })
                Separator()
                Item(PolygonTool.name, onClick = { Controller.tool(PolygonTool()) })
                Separator()
                Item(TrashTool.name, onClick = { Controller.tool(TrashTool()) })
            }
            Menu("Справка", mnemonic = 'I') {
                Item("Инструкция", onClick = { Controller.instruction() })
                Item("О программе", onClick = { Controller.about() })
            }
        }
    }

     object Controller {
            var save: () -> Unit = {}
            val tool: (Tool) -> Unit = {}
            val instruction: () -> Unit = {}
            var about: () -> Unit = {}
    }
}
