package ru.nsu.ccfit.kivis.button

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Droplet
import compose.icons.feathericons.MinusSquare
import ru.nsu.ccfit.kivis.dialog.PenDialog
import ru.nsu.ccfit.kivis.dialog.PolygonDialog
import ru.nsu.ccfit.kivis.tool.PenTool
import ru.nsu.ccfit.kivis.tool.PolygonTool
import ru.nsu.ccfit.kivis.tool.Tool

class PolygonButton(private val currentTool: MutableState<Tool>) : Button() {
    private val name: String = "Многоугольник"
    private val viewDialog = mutableStateOf(false)

    override fun handleClick() {
        viewDialog.value = true
    }

    private fun changeTool(polygonTool: PolygonTool) {
        currentTool.value = polygonTool
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun render() {
        val dialog = remember { viewDialog }
        TooltipArea(
            tooltip = {
                Surface(
                    modifier = Modifier.shadow(4.dp),
                    color = Color(0, 0, 0, 255),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = name,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            },
            modifier = Modifier.padding(start = 40.dp),
            delayMillis = 600,
            tooltipPlacement = TooltipPlacement.CursorPoint(
                alignment = Alignment.BottomEnd
            )
        ) {
            IconButton(onClick = { handleClick() }) {
                Icon(FeatherIcons.MinusSquare, contentDescription = "Localized description")
            }
            if (dialog.value) {
                PolygonDialog({ changeTool(it) }, { dialog.value = false })
            }
        }

    }
}