package ru.nsu.ccfit.kivis.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.unit.dp


class PaintCanvas(
    val image: MutableState<ImageBitmap>,
    private val isPaint: MutableState<Boolean>
) : Renderable {
    val offsetClick = mutableStateOf(Offset(0f, 0f) to Offset(0f, 0f))
    var offsetPress = (Offset(0F, 0F))
    var offsetRelease = (Offset(0F, 0F))

    private fun initCanvas(weight: Int, height: Int): ImageBitmap {
        val preBitmap = ImageBitmap(
            width = weight,
            height = height,
            config = ImageBitmapConfig.Argb8888,
            colorSpace = ColorSpaces.Srgb
        )
        val canvas = Canvas(image = preBitmap)
        val paint = Paint()
        paint.color = Color.White
        paint.style = PaintingStyle.Fill
        canvas.drawRect(Rect(Offset(0F, 0F), Offset(weight.toFloat(), height.toFloat())), paint)
        return preBitmap
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun render() {
        var positionRelease: Offset
        var positionPress: Offset
        Canvas(modifier = Modifier.size(1000.dp, 1000.dp)
            .onPointerEvent(PointerEventType.Release) {
                positionRelease = it.changes.first().position
                offsetRelease = positionRelease
                if (0 <= positionRelease.x && positionRelease.x < size.width) {
                    if (0 <= positionRelease.y && positionRelease.y < size.height) {
                        offsetClick.value = offsetRelease to offsetPress
                    }
                }
            }.onPointerEvent(PointerEventType.Press) {
                positionPress = it.changes.first().position
                offsetPress = positionPress
            }) {
            if (!isPaint.value) {
                isPaint.value = true
                image.value = initCanvas(size.width.toInt(), size.height.toInt())
            }
            drawImage(image = image.value)
        }
    }
}