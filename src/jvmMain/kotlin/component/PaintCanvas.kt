package ru.nsu.ccfit.kivis.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.*
import ru.nsu.ccfit.kivis.tool.PenTool


class PaintCanvas(
    val image: MutableState<ImageBitmap>,
    val isPaint: MutableState<Boolean>,
    val counter: MutableState<Long>
) : Renderable {
    var offsetPress = (Offset(0F, 0F))
    var offsetRelease = (Offset(0F, 0F))

    private fun initCanvas(weight: Int, height: Int): ImageBitmap {
        val preBitmap = ImageBitmap(
            width = weight,
            height = height,
            config = ImageBitmapConfig.Rgb565,
            hasAlpha = false
        )
        val canvas = Canvas(image = preBitmap)
        val paint = Paint()
        paint.color = Color.White
        paint.style = PaintingStyle.Fill
//        val floatArray = Array<Point>(4) {
//            Point(0F, 0F)
//            Point(0F, weight.toFloat())
//            Point(height.toFloat(), 0F)
//            Point(height.toFloat(), weight.toFloat())
//        }
//            canvas.nativeCanvas.drawPolygon(floatArray, paint.asFrameworkPaint())
//        canvas.nativeCanvas.save()
        canvas.drawRect(Rect(Offset(0F, 0F), Offset(weight.toFloat(), height.toFloat())), paint)
        //canvas.drawCircle(Offset((weight/2).toFloat(),(height/2).toFloat()),14000F,paint)
        return preBitmap
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun render() {
        val dialogTool = remember { Menu.Controller.tool }
        Canvas(modifier = Modifier.fillMaxSize()
            .onPointerEvent(PointerEventType.Release) {
                val position = it.changes.first().position
                offsetRelease = position
                PenTool().draw(this@PaintCanvas)
            }.onPointerEvent(PointerEventType.Press) {
                val position = it.changes.first().position
                offsetPress = position
            }) {
            if (!isPaint.value) {
                isPaint.value = true
                image.value = initCanvas(size.width.toInt(), size.height.toInt())
            }
            drawImage(image = image.value)
        }
    }
}