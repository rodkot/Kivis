package ru.nsu.ccfit.kivis.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.*


class PaintCanvas() {
    var isPaint: Boolean = true

    val offsetClick = mutableStateOf(Offset(0f, 0f) to Offset(0f, 0f))
    private var offsetPress = (Offset(0F, 0F))
    private var offsetRelease = (Offset(0F, 0F))


    fun start() {
        isPaint = true
    }

    fun stop() {
        isPaint = false
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun render(image: KivisImage, onClick: (KivisImage, Offset, Offset) -> Unit) {
        var positionRelease: Offset
        var positionPress: Offset
        Image(image.toComposeImageBitmap(), contentDescription = "Изображение",
            modifier = Modifier.fillMaxSize()
                .onPointerEvent(PointerEventType.Release) {
                    positionRelease = it.changes.first().position
                    offsetRelease = positionRelease
                    if (0 <= positionRelease.x && positionRelease.x < size.width) {
                        if (0 <= positionRelease.y && positionRelease.y < size.height) {
                            if (isPaint) {
                                offsetClick.value = offsetPress to offsetRelease
                                onClick.invoke(image, offsetPress, offsetRelease)
                            }
                        }
                    }
                }.onPointerEvent(PointerEventType.Press) {
                    positionPress = it.changes.first().position
                    offsetPress = positionPress
                })
    }


}