package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.DTII4Border
import lib.sRAD.kotlin.gui.component.Theme
import lib.sRAD.kotlin.gui.component.fontText
import java.awt.Color
import java.awt.Font
import javax.swing.JTextField
import javax.swing.border.Border

class STextField : JTextField {
    //DEFAULT
    constructor() : super() {}

    //EMPTY
    constructor(x: Int, y: Int, width: Int, height: Int) {
        setProperties(x, y, width, height)
    }

    constructor(x: Int, y: Int, width: Int, height: Int, text: String?) {
        setProperties(x, y, width, height, text)
    }

    fun setProperties(x: Int, y: Int, width: Int, height: Int) {
        setProperties(x, y, width, height, true, Theme.fFg, Theme.bg2, fontText, DTII4Border, LEFT)
    }

    fun setProperties(x: Int, y: Int, width: Int, height: Int, text: String?) {
        setProperties(x, y, width, height)
        setText(text)
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

    val isNotEmpty: Boolean
        get() = !text.isEmpty()
}