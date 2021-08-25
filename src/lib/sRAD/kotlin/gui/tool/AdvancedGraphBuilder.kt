package lib.sRAD.gui.tool

import javax.swing.border.Border
import javax.swing.BorderFactory
import java.awt.geom.Area
import java.awt.geom.RoundRectangle2D
import lib.sRAD.gui.tool.AdvancedGraphBuilder
import java.awt.*
import javax.swing.border.AbstractBorder
import java.awt.geom.Ellipse2D
import java.awt.geom.RectangularShape

object AdvancedGraphBuilder {
    fun getBlurredBorder(colorBase: Color, grosor: Int): Border? {
        var bordeFinal: Border? = null
        val bordeInicial = BorderFactory.createLineBorder(colorBase, 1, true)
        var siguienteColor = Color(colorBase.red + 5, colorBase.green + 5, colorBase.blue + 5)
        var contador = 0
        while (siguienteColor.red < 251 && siguienteColor.green < 251 && siguienteColor.blue < 251 && contador < grosor) {
            val bordeExterno = BorderFactory.createLineBorder(siguienteColor, 1, true)
            bordeFinal = if (contador == 0) BorderFactory.createCompoundBorder(bordeExterno, bordeInicial) else {
                BorderFactory.createCompoundBorder(bordeExterno, bordeFinal)
            }
            siguienteColor = Color(siguienteColor.red + 5, siguienteColor.green + 5, siguienteColor.blue + 5)
            contador++
        }
        return bordeFinal
    }

    fun getRoundedBorder(color: Color?, radio: Int, esLineal: Boolean, imagen: Image?): Border {
        return object : Border {
            override fun paintBorder(c: Component, g: Graphics, x: Int, y: Int, ancho: Int, alto: Int) {
                val g2 = g as Graphics2D
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)
                val area: Area
                val padreContenedor: Component = c.parent
                val rectanguloBordeado: RoundRectangle2D = RoundRectangle2D.Double()
                rectanguloBordeado.setRoundRect(
                    java.lang.Double.valueOf(x.toDouble()),
                    java.lang.Double.valueOf(y.toDouble()),
                    ancho - 1.0,
                    alto - 1.0,
                    java.lang.Double.valueOf(radio.toDouble()),
                    java.lang.Double.valueOf(radio.toDouble())
                )
                if (esLineal) {
                    drawBackground(c, padreContenedor, imagen, g2, ancho, alto)
                    area = drawBorder(c, g2, color, x, y, ancho, alto, rectanguloBordeado)
                } else {
                    area = drawBorder(c, g2, color, x, y, ancho, alto, rectanguloBordeado)
                    drawBackground(c, padreContenedor, imagen, g2, ancho, alto)
                }
                g2.clip = null
                g2.draw(area)
            }

            override fun getBorderInsets(c: Component): Insets {
                return Insets(2, 2, 2, 2)
            }

            override fun isBorderOpaque(): Boolean {
                return false
            }
        }
    }

    fun getCircularBorder(color: Color?, esLineal: Boolean, imagen: Image?): AbstractBorder {
        return object : AbstractBorder() {
            override fun paintBorder(c: Component, g: Graphics, x: Int, y: Int, ancho: Int, alto: Int) {
                val g2 = g as Graphics2D
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)
                val area: Area
                val padreContenedor: Component = c.parent
                val circulo: Ellipse2D = Ellipse2D.Double()
                circulo.setFrameFromCenter(Point(x + ancho / 2, y + alto / 2), Point(ancho, alto))
                if (esLineal) {
                    drawBackground(c, padreContenedor, imagen, g2, ancho, alto)
                    area = drawBorder(c, g2, color, x, y, ancho, alto, circulo)
                } else {
                    area = drawBorder(c, g2, color, x, y, ancho, alto, circulo)
                    drawBackground(c, padreContenedor, imagen, g2, ancho, alto)
                }
                g2.clip = null
                g2.draw(area)
            }
        }
    }

    fun drawBackground(
        c: Component,
        padreContenedor: Component,
        imagen: Image?,
        g2: Graphics2D,
        ancho: Int,
        alto: Int
    ) {
        if (imagen != null) {
            g2.drawImage(
                imagen,
                0, 0, imagen.getWidth(null), imagen.getHeight(null),
                c.x, c.y, imagen.getWidth(null) + c.x, imagen.getHeight(null) + c.y,
                c
            )
        } else {
            g2.color = padreContenedor.background
            g2.fillRect(0, 0, ancho, alto)
        }
    }

    fun drawBorder(
        c: Component,
        g2: Graphics2D,
        color: Color?,
        x: Int,
        y: Int,
        ancho: Int,
        alto: Int,
        figura: RectangularShape?
    ): Area {
        if (color == null) {
            g2.paint = c.background
        } else {
            g2.paint = color
        }
        val area = Area(figura)
        val rectangulo = Rectangle(0, 0, ancho, alto)
        val regionBorde = Area(rectangulo)
        regionBorde.subtract(area)
        g2.clip = regionBorde
        return area
    }
}