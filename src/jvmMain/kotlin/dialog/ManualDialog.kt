package ru.nsu.ccfit.kivis.dialog

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ManualDialog(cancelClick: () -> Unit) {

    AlertDialog(
        onDismissRequest = {
            // cancelClick.invoke()
        },
        title = { Text(text = "Инструкция Kivis") },
        text = {

        },
        modifier = Modifier.width(300.dp),
        buttons = {
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    cancelClick.invoke()
                }
            ) {
                Text("Отмена")
            }
        }

    )
}