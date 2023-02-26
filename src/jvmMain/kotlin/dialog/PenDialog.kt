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
import ru.nsu.ccfit.kivis.tool.PenTool

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PenDialog(confirmClick: (PenTool) -> Unit, cancelClick: () -> Unit) {
    val currentColor = mutableStateOf(HsvColor.from(Color.Red))

    val branch = mutableStateOf(0F)

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
                        confirmClick.invoke(PenTool(branch.value.toInt(), currentColor.value.toColor()))
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