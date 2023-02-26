package ru.nsu.ccfit.kivis.component

import androidx.compose.runtime.Composable

interface Renderable {
    @Composable
    fun render();
}