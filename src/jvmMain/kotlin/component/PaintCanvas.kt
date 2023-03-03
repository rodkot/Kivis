package ru.nsu.ccfit.kivis.component

import androidx.compose.foundation.Canvas
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
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB


class PaintCanvas(
    val image: MutableState<BufferedImage>,
    private val isPaint: MutableState<Boolean>
) : Renderable {
    val offsetClick = mutableStateOf(Offset(0f, 0f) to Offset(0f, 0f))
    var offsetPress = (Offset(0F, 0F))
    var offsetRelease = (Offset(0F, 0F))

    private fun initCanvas(weight: Int, height: Int): BufferedImage {
        val preBitmap = BufferedImage(
            weight,
            height,
            TYPE_INT_RGB
        )
        val graphics: Graphics2D = preBitmap.createGraphics()
        graphics.background = java.awt.Color.WHITE
        graphics.clearRect(0, 0, preBitmap.width, preBitmap.height)
        graphics.dispose()

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
            drawImage(image = image.value.toComposeImageBitmap())
        }
    }
}