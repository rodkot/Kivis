package ru.nsu.ccfit.kivis.button

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Edit3
import ru.nsu.ccfit.kivis.tool.PenTool
import ru.nsu.ccfit.kivis.tool.Tool

class PenButton(private val currentTool: MutableState<Tool>) : Button() {
    private val name: String = "Линия"

    override fun handleClick() {
        //val i = currentImageBitmap.value
//        val c = androidx.compose.ui.graphics.Canvas(currentImageBitmap.value)
//        val paint = Paint()
//
//        paint.color =Color.White
//        paint.style  = PaintingStyle.Fill
//        c.drawCircle(Offset(50F ,50F), 50F, paint)
        currentTool.value = PenTool()


    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun dialog(){
        AlertDialog(
            onDismissRequest = {
                //openDialog.value = false
            },
            title = { Text(text = "Подтверждение действия") },
            text = { Text("Вы действительно хотите удалить выбранный элемент?") },
            buttons = {
                Button(
                    onClick = { //openDialog.value = false
                    }
                ) {
                    Text("OK", fontSize = 22.sp)
                }
            }
        )
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun render() {
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
                Icon(FeatherIcons.Edit3, contentDescription = "Localized description")
            }
        }

    }
}