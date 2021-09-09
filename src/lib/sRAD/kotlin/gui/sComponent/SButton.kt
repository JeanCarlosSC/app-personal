package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.*
import javax.swing.JButton
import javax.swing.Icon
import java.awt.Font
import java.awt.Color
import java.awt.Cursor
import java.awt.event.ActionListener
import java.awt.event.MouseEvent
import javax.swing.border.Border
import java.awt.event.MouseListener

open class SButton : JButton {

    //- - - - - JButton - - - - -

    /**
     * default empty button
     */
    constructor() : super()

    //- - - - - Icon button - - - - -

    constructor(x: Int, y: Int, icon: Icon?) {
        setProperties(x, y, icon, handCursor)
    }

    constructor(x: Int, y: Int, icon: Icon?, cursor: Cursor?) {
        setProperties(x, y, icon, cursor)
    }

    fun setProperties(x: Int, y: Int, icon: Icon?, cursor: Cursor?) {
        setLocation(x, y)
        isContentAreaFilled = false
        border = null
        setCursor(cursor)
        isFocusable = false
        isFocusPainted = false
        if (icon != null) {
            this.setSize(icon.iconWidth, icon.iconHeight)
            this.icon = icon
        }
    }

    //- - - - - Text Button - - - - -

    /**
     * Text button
     */
    constructor(x: Int, y: Int, text: String, actionListener: ActionListener) {
        setProperties(x, y, text, actionListener)
    }

    /**
     * Text button
     */
    private fun setProperties(x: Int, y: Int, text: String, actionListener: ActionListener) {
        addActionListener(actionListener)
        setProperties(x, y, 100, 32, text, handCursor, fontTitleMini, Theme.btBg, DTII14, DTII4Border, DTII8, DTII4Border)
    }

    /**
     * Text button
     */
    fun setProperties(x: Int, y: Int, width: Int, height: Int, text: String?, cursor: Cursor?, font: Font?, background: Color?, foreground: Color?,
                      border: Border?, backgroundEntered: Color?, borderEntered: Border?) {
        setProperties(x, y, width, height, cursor, background)
        setText(text)
        setFont(font)
        setForeground(foreground)
        setBorder(border)
        horizontalAlignment = CENTER
        addMouseListener(object : MouseListener {
            override fun mouseClicked(e: MouseEvent) {}
            override fun mousePressed(e: MouseEvent) {}
            override fun mouseReleased(e: MouseEvent) {}
            override fun mouseEntered(e: MouseEvent) {
                setBackground(backgroundEntered)
                setBorder(borderEntered)
            }

            override fun mouseExited(e: MouseEvent) {
                setBackground(background)
                setBorder(border)
            }
        })
    }

    //- - - - - Standard button - - - - -

    /**
     * Standard button
     */
    fun setProperties(x: Int, y: Int, width: Int, height: Int, cursor: Cursor?, background: Color?) {
        setSize(width, height)
        setLocation(x, y)
        setCursor(cursor)
        setBackground(background)
        isContentAreaFilled = true
        isFocusPainted = false
    }

    //- - - - - Text button filled with icon

    /**
     * text button filled with icon
     */
    fun setProperties(x: Int, y: Int, width: Int, height: Int, cursor: Cursor?, background: Color?, isSolid: Boolean?, icon: Icon?) {
        this.setProperties(x, y, icon, cursor)
        this.setSize(width, height)
        this.background = background
        this.isContentAreaFilled = isSolid!!
    }

}