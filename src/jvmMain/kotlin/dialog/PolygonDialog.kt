package ru.nsu.ccfit.kivis.dialog

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.nsu.ccfit.kivis.component.*
import ru.nsu.ccfit.kivis.tool.PolygonTool

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PolygonDialog(confirmClick: (PolygonTool) -> Unit, cancelClick: () -> Unit) {
    val countVertices = mutableStateOf(2f)
    val radius = mutableStateOf(0f)
    val rotation = mutableStateOf(0f)

    AlertDialog(
        onDismissRequest = {
            // cancelClick.invoke()
        },
        title = { Text(text = "Настройка инстумента ${PolygonTool.name}") },
        text = {
            Column {
                countVerticesBox(countVertices, 3f..5f, 2)
                rotationBox(rotation, 0f..360f, 360)
                radiusBox(radius, 1f..100f, 100)
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
                        confirmClick.invoke(
                            PolygonTool(
                                rotation = rotation.value,
                                radius = radius.value,
                                countVertices = countVertices.value.toInt()
                            )
                        )
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