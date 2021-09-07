package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.DTII14
import lib.sRAD.kotlin.gui.component.fontText
import java.awt.Color
import java.awt.Cursor
import java.awt.Font
import javax.swing.ImageIcon
import javax.swing.JLabel

class SLabel : JLabel {
    //DEFAULT LABEL
    /**
     * default
     */
    constructor() : super()

    //ICON LABEL
    constructor(x: Int, y: Int, icon: ImageIcon) {
        setProperties(x, y, icon, null)
    }

    constructor(x: Int, y: Int, icon: ImageIcon, cursor: Cursor?) {
        setProperties(x, y, icon, cursor)
    }

    /**
     * Text Label
     */
    constructor(x: Int, y: Int, width: Int, height: Int=28, text: String?) {
        setProperties(x, y, width, height, text, fontText, DTII14, LEFT, null)
    }

    /**
     * icon label
     */
    fun setProperties(x: Int, y: Int, icon: ImageIcon) {
        this.setSize(icon.iconWidth, icon.iconHeight)
        this.setLocation(x, y)
        this.icon = icon
    }

    fun setProperties(x: Int, y: Int, icon: ImageIcon, cursor: Cursor?) {
        setProperties(x, y, icon)
        this.cursor = cursor
    }

    constructor(x: Int, y: Int, width: Int, height: Int, text: String?, hAlignment: Int) {
        setProperties(x, y, width, height, text, fontText, DTII14, hAlignment, null)
    }

    constructor(x: Int, y: Int, width: Int, height: Int, text: String?, font: Font?) {
        setProperties(x, y, width, height, text, font, DTII14, LEFT, null)
    }

    constructor(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        text: String?,
        font: Font?,
        foreground: Color?,
        hAlignment: Int,
        background: Color?
    ) {
        setProperties(x, y, width, height, text, font, foreground, hAlignment, background)
    }

    /**
     * text label
     */
    fun setProperties(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        text: String?,
        font: Font?,
        foreground: Color?,
        hAlignment: Int
    ) {
        this.setBounds(x, y, width, height)
        this.foreground = foreground
        this.font = font
        this.text = text
        horizontalAlignment = hAlignment
    }

    fun setProperties(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        text: String?,
        font: Font?,
        foreground: Color?,
        hAlignment: Int,
        background: Color?
    ) {
        setProperties(x, y, width, height, text, font, foreground, hAlignment)
        this.background = background
    }
}