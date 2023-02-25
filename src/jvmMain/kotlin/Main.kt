package ru.nsu.ccfit.kivis

import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import java.awt.Dimension

class SizeWindows {
    companion object {
        fun height(): Int = 640
        fun weight(): Int = 480
    }
}


fun main() = application {

    Window(
        onCloseRequest = ::exitApplication, title = "Kivis", icon =  painterResource("kivi_logo.png"), resizable = false, state = WindowState(
            position = WindowPosition.Aligned(Alignment.Center)
        )
    ) {
        Menu().render(this)
        window.minimumSize = Dimension(SizeWindows.height(), SizeWindows.weight())
        MainWindow()
    }
}