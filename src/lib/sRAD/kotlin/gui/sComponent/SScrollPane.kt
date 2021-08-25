package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.DTII3
import lib.sRAD.kotlin.gui.component.DTII4Border
import lib.sRAD.kotlin.gui.component.DTII7
import lib.sRAD.kotlin.gui.component.SP4
import java.awt.*
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JScrollBar
import javax.swing.JScrollPane
import javax.swing.plaf.basic.BasicScrollBarUI

class SScrollPane : JScrollPane {
    constructor(x: Int, y: Int, width: Int, height: Int) {
        setProperties(x, y, width, height)
    }

    constructor(x: Int, y: Int, width: Int, height: Int, component: Component?) {
        setProperties(x, y, width, height)
        setViewportView(component)
    }

    fun setProperties(x: Int, y: Int, width: Int, height: Int) {
        setBounds(x, y, width, height)
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED)
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED)
        verticalScrollBar.setUI(customScroll)
        horizontalScrollBar.setUI(customScroll)
        background = DTII3
        border = DTII4Border
    }

    companion object {
        //Advanced graphic builder
        @JvmStatic
        val customScroll: BasicScrollBarUI
            get() = getCustomScroll(7, 10, DTII3, DTII7, SP4)

        fun getCustomScroll(
            grosor: Int,
            radio: Int,
            colorFondo: Color?,
            colorBarraNormal: Color?,
            colorBarraArrastrada: Color?
        ): BasicScrollBarUI {
            return object : BasicScrollBarUI() {
                private val d = Dimension()
                override fun createDecreaseButton(orientation: Int): JButton {
                    val boton = JButton()
                    boton.preferredSize = d
                    return boton
                }

                override fun createIncreaseButton(orientation: Int): JButton {
                    val boton = JButton()
                    boton.preferredSize = d
                    return boton
                }

                override fun paintTrack(g: Graphics, c: JComponent, r: Rectangle) {
                    g.color = colorFondo
                    g.fillRect(r.x, r.y, r.width, r.height)
                }

                override fun paintThumb(g: Graphics, c: JComponent, r: Rectangle) {
                    val g2 = g.create() as Graphics2D
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)
                    val sb = c as JScrollBar
                    if (!sb.isEnabled) {
                        return
                    } else if (isDragging) {
                        g2.paint = colorBarraArrastrada
                    } else if (isThumbRollover) {
                        g2.paint = colorBarraNormal
                    } else {
                        g2.paint = colorBarraNormal
                    }
                    if (r.width < r.height) {
                        g2.fillRoundRect((r.width - grosor) / 2, r.y, grosor, r.height, radio, radio)
                    } else {
                        g2.fillRoundRect(r.x, (r.height - grosor) / 2, r.width, grosor, radio, radio)
                    }
                }
            }
        }
    }
}