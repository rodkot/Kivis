package ru.nsu.ccfit.kivis.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor

@Composable
fun widthBox(widthBrunch: MutableState<Float>, range: ClosedFloatingPointRange<Float>, steps: Int) {
    Box(contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Толщина: ${widthBrunch.value.toInt()}", color = Color.Black)
            Slider(
                value = widthBrunch.value,
                modifier = Modifier.width(200.dp).height(10.dp),
                valueRange = range,
                steps = steps,
                onValueChange = {
                    widthBrunch.value = it}
            )
        }
    }
}

@Composable
fun rotationBox(rotation: MutableState<Float>, range: ClosedFloatingPointRange<Float>, steps: Int) {
    Box(contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Поворот: ${rotation.value.toInt()} градусов", color = Color.Black)
            Slider(
                value = rotation.value,
                modifier = Modifier.width(200.dp).height(10.dp),
                valueRange = range,
                steps = steps,
                onValueChange = {
                    rotation.value = it}
            )
        }
    }
}

@Composable
fun radiusBox(radius: MutableState<Float>, range: ClosedFloatingPointRange<Float>, steps: Int) {
    Box(contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Радиус: ${radius.value.toInt()}", color = Color.Black)
            Slider(
                value = radius.value,
                modifier = Modifier.width(200.dp).height(10.dp),
                valueRange = range,
                steps = steps,
                onValueChange = {
                    radius.value = it}
            )
        }
    }
}

@Composable
fun countVerticesBox(countVertices: MutableState<Float>, range: ClosedFloatingPointRange<Float>, steps: Int) {
    Box(contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Количество вершин: ${countVertices.value.toInt()} ", color = Color.Black)
            Slider(
                value = countVertices.value,
                modifier = Modifier.width(200.dp).height(10.dp),
                valueRange = range,
                steps = steps,
                onValueChange = {
                    countVertices.value = it}
            )
        }
    }
}

@Composable
fun colorBox(currentColor: MutableState<HsvColor>){
    Box() {
        Column {
            Text(text = "Цвет:", color = Color.Black)
            Spacer(
                modifier = Modifier.background(
                    currentColor.value.toColor(),
                    shape = RectangleShape
                ).fillMaxWidth()
                    .height(20.dp)
            )
            Spacer(Modifier.fillMaxWidth().padding(10.dp))
            ClassicColorPicker(
                color = HsvColor.from(Color.Red),
                modifier = Modifier.fillMaxWidth().height(200.dp),
                showAlphaBar = false,
                onColorChanged = { hsvColor: HsvColor ->
                    currentColor.value = hsvColor
                })
        }

    }
}