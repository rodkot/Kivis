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
fun PolygonDialog(confirmClick: () -> Unit, cancelClick: () -> Unit) {
    val countVertices = mutableStateOf(PolygonTool.countVertices.toFloat())
    val radius = mutableStateOf(PolygonTool.radius)
    val rotation = mutableStateOf(PolygonTool.rotation)

    AlertDialog(
        onDismissRequest = {
            // cancelClick.invoke()
        },
        title = { Text(text = "Настройка инстумента ${PolygonTool.name}") },
        text = {
            Column {
                countVerticesBox(countVertices, 3f..13f, 10)
                Spacer(Modifier.height(10.dp))
                rotationBox(rotation, 0f..360f, 360)
                Spacer(Modifier.height(10.dp))
                radiusBox(radius, 10f..100f, 110)
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

                        PolygonTool.rotation = rotation.value
                        PolygonTool.radius = radius.value
                        PolygonTool.countVertices = countVertices.value.toInt()

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