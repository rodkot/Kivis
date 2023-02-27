package ru.nsu.ccfit.kivis.dialog

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import ru.nsu.ccfit.kivis.component.colorBox
import ru.nsu.ccfit.kivis.component.widthBox
import ru.nsu.ccfit.kivis.tool.FillTool
import ru.nsu.ccfit.kivis.tool.PenTool

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun FillDialog(default: MutableState<FillTool>,confirmClick: () -> Unit, cancelClick: () -> Unit) {
    val currentColor = mutableStateOf(HsvColor.from(default.value.color))

    AlertDialog(
        onDismissRequest = {
            // cancelClick.invoke()
        },
        title = { Text(text = "Настройка инстумента ${FillTool.name}") },
        text = {
            Column {
                colorBox(currentColor)
            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.weight(1f).padding(8.dp),
                    onClick = {
                        default.value = FillTool(currentColor.value.toColor())
                        confirmClick.invoke()
                        cancelClick.invoke()
                    }
                ) {
                    Text("Применить")
                }
                Button(
                    modifier = Modifier.weight(1f).padding(8.dp),
                    onClick = {
                        cancelClick.invoke()
                    }
                ) {
                    Text("Отмена")
                }
            }
        }
    )
}