package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.DTII1
import lib.sRAD.kotlin.gui.component.DTII14
import lib.sRAD.kotlin.gui.component.DTII1Border
import lib.sRAD.kotlin.gui.component.fontText
import java.awt.Color
import java.awt.Font
import javax.swing.JTextArea
import javax.swing.border.Border

class STextArea : JTextArea {
    /**
     * GET_INFO_TYPE
     * @param x
     * @param y
     * @param width
     * @param height
     */
    constructor(x: Int, y: Int, width: Int, height: Int) {
        setProperties(x, y, width, height, true, true, "", DTII14, DTII1, fontText, DTII1Border, LEFT_ALIGNMENT)
    }

    /**
     * SHOW_INFO_TYPE
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     */
    constructor(x: Int, y: Int, width: Int, height: Int, text: String?) {
        setProperties(x, y, width, height, false, true, text, DTII14, null, fontText, null, LEFT_ALIGNMENT)
    }

    fun setProperties(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        editable: Boolean?,
        lineWrap: Boolean?,
        text: String?,
        foreground: Color?,
        background: Color?,
        font: Font?,
        border: Border?,
        hAlignment: Float?
    ) {
        this.setBounds(x, y, width, height)
        setText(text)
        isEditable = editable!!
        setForeground(foreground)
        setFont(font)
        setBackground(background)
        caretColor = foreground
        setBorder(border)
        wrapStyleWord = lineWrap!!
        setLineWrap(lineWrap)
        alignmentX = hAlignment!!
    }

    companion object {
        const val SHOW_INFO_TYPE = 0
        const val GET_INFO_TYPE = 1
    }
}