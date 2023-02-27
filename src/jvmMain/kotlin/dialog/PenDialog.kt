package ru.nsu.ccfit.kivis.dialog

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.godaddy.android.colorpicker.HsvColor
import ru.nsu.ccfit.kivis.component.colorBox
import ru.nsu.ccfit.kivis.component.widthBox
import ru.nsu.ccfit.kivis.tool.PenTool

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PenDialog(default: MutableState<PenTool>,confirmClick: () -> Unit, cancelClick: () -> Unit) {
    val currentColor = mutableStateOf(HsvColor.from(default.value.color))
    val branch = mutableStateOf(default.value.brash.toFloat())

    AlertDialog(
        onDismissRequest = {
           // cancelClick.invoke()
        },
        title = { Text(text = "Настройка инстумента ${PenTool.name}") },
        text = {
            Column {
                widthBox(branch, 0f..10f, 10)
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
                        default.value = PenTool(branch.value.toInt(), currentColor.value.toColor())
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