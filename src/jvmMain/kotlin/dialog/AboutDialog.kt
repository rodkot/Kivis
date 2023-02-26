package ru.nsu.ccfit.kivis.dialog

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.godaddy.android.colorpicker.HsvColor
import ru.nsu.ccfit.kivis.component.colorBox
import ru.nsu.ccfit.kivis.tool.FillTool

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun AboutDialog(cancelClick: () -> Unit) {

    AlertDialog(
        onDismissRequest = {
            // cancelClick.invoke()
        },
        title = { Text(text = "О программе Kivis") },
        text = {
            Column {
                Text(text = "Разработчик:")
                Text(text = "Котов Родион")
                Text(text = "студент 3 курса ФИТ НГУ")
                Spacer(Modifier.width(20.dp))
                Text(text = "Программа созадана в рамках курса \"Компьютерная и инженерная графика\" ")
                Spacer(Modifier.width(20.dp))
                Text(text = "г. Новосибириск")
                Spacer(Modifier.width(20.dp))
                Text(text = "2020 год")
            }
        },
        buttons = {
            Button(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                onClick = {
                    cancelClick.invoke()
                }
            ) {
                Text("Отмена")
            }
        }

    )
}