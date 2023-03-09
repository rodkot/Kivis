package ru.nsu.ccfit.kivis.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.unit.IntSize
import ru.nsu.ccfit.kivis.draw.clear
import ru.nsu.ccfit.kivis.draw.resize
import java.awt.image.BufferedImage


class PaintCanvas(
    val image: MutableState<BufferedImage>,
    val size: MutableState<IntSize>,
) : Renderable {
    private var start: Boolean = false
    var isPaint: Boolean = false

    val offsetClick = mutableStateOf(Offset(0f, 0f) to Offset(0f, 0f))
    var offsetPress = (Offset(0F, 0F))
    var offsetRelease = (Offset(0F, 0F))


    fun start() {
        isPaint = true
    }

    fun stop() {
        isPaint = false
    }


    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun render() {
        var positionRelease: Offset
        var positionPress: Offset
        Image(image.value.toComposeImageBitmap(), contentDescription = "Изображение",
            modifier = Modifier.fillMaxSize()
                .onPointerEvent(PointerEventType.Release) {
                    positionRelease = it.changes.first().position
                    offsetRelease = positionRelease
                    if (0 <= positionRelease.x && positionRelease.x < size.width) {
                        if (0 <= positionRelease.y && positionRelease.y < size.height) {
                            if (isPaint) offsetClick.value = offsetRelease to offsetPress
                        }
                    }
                }.onPointerEvent(PointerEventType.Press) {
                    positionPress = it.changes.first().position
                    offsetPress = positionPress
                })
        if (!start && size.value != IntSize.Zero) {
            start = true
            clear()
            resize()
        }
    }
}