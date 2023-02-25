package ru.nsu.ccfit.kivis

import androidx.compose.runtime.Composable

interface Renderable {
    @Composable
    fun render();
}