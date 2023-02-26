package ru.nsu.ccfit.kivis.component

import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.JPanel

class DrawPanel() : JPanel() {
    private val draw = true
    private var x = 0;
    private var y: Int = 0;
    var xd: Int = -1;
    var yd: Int = -1
    private var img: BufferedImage? = null

    init {
        mouseListeners()
    }

    private fun mouseListeners() {
        addMouseListener(object : MouseAdapter() {

            override fun mousePressed(e: MouseEvent?) {
                super.mousePressed(e)
                xd = (e?.x ?: 0).also { x = it }
                yd = (e?.y ?: 0).also { y = it }
            }

            override fun mouseReleased(e: MouseEvent?) {
                super.mouseReleased(e)

//                val g2 = graphics as Graphics2D
                //    g2.color = Color.RED
                //  g2.stroke = BasicStroke(4f)

                img!!.graphics.drawLine(x, y, e?.x ?: 0, e?.y ?: 0)
                // print(img?.graphics)
                repaint()
                xd = -1
                yd = -1
            }
        })
    }

    override fun paintComponent(g: Graphics?) {
        if (img == null) {
            img = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val g2 = img?.graphics as Graphics2D
            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            )
            g2.color = Color.DARK_GRAY
            g2.fill(Rectangle(width, height))
            clean()
        }
        if (draw) {
            val g2 = g as Graphics2D
//            g2.color = Color.RED
//            g2.stroke = BasicStroke(4f)
//            g2.drawLine(0, 0, width - 1, height - 1)


            // int color = img.getRGB(x, y);

            //  * img.setRGB(x, y, Color.GREEN.getRGB());


            g2.drawImage(img, 0, 0, null);
        }
    }

    private fun savePaint() {
        val img = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        printAll(img.graphics)

        try {
            ImageIO.write(img, "jpg", File("panel.jpg"))
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    fun clean() {

    }
}