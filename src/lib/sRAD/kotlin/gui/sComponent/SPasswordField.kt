package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.DTII4Border
import lib.sRAD.kotlin.gui.component.Theme
import lib.sRAD.kotlin.gui.component.fontText
import java.awt.Color
import java.awt.Font
import javax.swing.JPasswordField
import javax.swing.border.Border

class SPasswordField(x: Int, y: Int, width: Int, height: Int) : JPasswordField() {
    fun setProperties(x: Int, y: Int, width: Int, height: Int) {
        setProperties(x, y, width, height, true, Theme.fFg, Theme.bg2, fontText, DTII4Border, LEFT)
    }

    fun setProperties(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        editable: Boolean?,
        foreground: Color?,
        background: Color?,
        font: Font?,
        border: Border?,
        hAlignment: Int
    ) {
        this.setBounds(x, y, width, height)
        isEditable = editable!!
        setForeground(foreground)
        setFont(font)
        setBackground(background)
        caretColor = foreground
        setBorder(border)
        horizontalAlignment = hAlignment
    }

    /**
     * Renovación del método getPassword() en javax.swing.JPasswordField.
     * @return password ingresada en el password field en forma de String.
     */
    val clave: String
        get() {
            var clave = ""
            val chars = password
            for (i in chars) {
                clave += i
            }
            return clave
        }

    init {
        setProperties(x, y, width, height)
    }
}