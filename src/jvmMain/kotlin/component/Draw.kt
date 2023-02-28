package ru.nsu.ccfit.kivis.component

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import ru.nsu.ccfit.kivis.tool.PenTool
import ru.nsu.ccfit.kivis.tool.TrashTool

//
//fun PaintCanvas.draw(trashTool: TrashTool){
//    val currentImage = image.value
//    val canvas = Canvas(image = image.value)
//    val paint = Paint()
//    paint.color = Color.Red
//    paint.style = PaintingStyle.Fill
//    canvas.drawRect(Rect(Offset(0F, 0F), Offset(currentImage.width.toFloat(), currentImage.height.toFloat())), paint)
//    canvas.drawLine(offsetPress, offsetRelease, paint)
//    counter.value++
//}

//fun PaintCanvas.draw(penTool: PenTool) {
//    val canvas = Canvas(image = image.value)
//    val paint = Paint()
//    paint.color = penTool.color
//    canvas.drawLine(offsetPress, offsetRelease, paint)
//    counter.value++
//}

fun PaintCanvas.drawLine(offsetA: Offset,offsetB: Offset, wigth:Int){

}