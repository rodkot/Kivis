package ru.nsu.ccfit.kivis

import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ru.nsu.ccfit.kivis.component.Menu
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