package ru.nsu.ccfit.kivis.dialog

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.nsu.ccfit.kivis.component.countVerticesBox
import ru.nsu.ccfit.kivis.component.radiusBox
import ru.nsu.ccfit.kivis.component.rotationBox
import ru.nsu.ccfit.kivis.tool.StarTool


@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun StarDialog(confirmClick: () -> Unit, cancelClick: () -> Unit) {
    val countPeek = mutableStateOf(StarTool.countPeek.toFloat())
    val radius = mutableStateOf(StarTool.radius)
    val rotation = mutableStateOf(StarTool.rotation)

    AlertDialog(
        onDismissRequest = {
            // cancelClick.invoke()
        },
        title = { Text(text = "Настройка инстумента ${StarTool.name}") },
        text = {
            Column {
                countVerticesBox(countPeek, 5f..16f, 11)
                Spacer(Modifier.height(10.dp))
                rotationBox(rotation, 0f..360f, 360)
                Spacer(Modifier.height(10.dp))
                radiusBox(radius, 10f..210f, 200)
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

                        StarTool.rotation = rotation.value
                        StarTool.radius = radius.value
                        StarTool.countPeek = countPeek.value.toInt()

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