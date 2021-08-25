package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.Theme.btBg
import lib.sRAD.kotlin.gui.component.Theme.fFg
import lib.sRAD.kotlin.gui.component.Theme.pB
import lib.sRAD.kotlin.gui.component.fontText
import javax.swing.JComboBox
import java.awt.Font
import java.awt.Color
import javax.swing.border.Border

class SComboBox(type: Int, x: Int, y: Int, width: Int, height: Int, opciones: Array<String?>?) :
    JComboBox<Any?>(opciones) {
    fun setProperties(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        font: Font?,
        foreground: Color?,
        background: Color?,
        border: Border?
    ) {
        setSize(width, height)
        setLocation(x, y)
        setBorder(border)
        setFont(font)
        setForeground(foreground)
        setBackground(background)
    }

    companion object {
        const val DECORADO = 1
    }

    init {
        if (type == DECORADO) {
            setProperties(x, y, width, height, fontText, fFg, btBg, pB)
        }
    }
}