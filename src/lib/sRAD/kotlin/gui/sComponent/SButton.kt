package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.*
import javax.swing.JButton
import javax.swing.Icon
import kotlin.jvm.JvmOverloads
import java.awt.Font
import java.awt.Color
import java.awt.Cursor
import java.awt.event.MouseEvent
import javax.swing.border.Border
import java.awt.event.MouseListener

class SButton : JButton {
    /**
     * default button
     */
    constructor() : super()

    //ICON BUTTON
    constructor(x: Int, y: Int, icon: Icon?) {
        setProperties(x, y, icon, handCursor)
    }

    constructor(x: Int, y: Int, icon: Icon?, cursor: Cursor?) {
        setProperties(x, y, icon, cursor)
    }

    //TEXT BUTTON
    @JvmOverloads
    constructor(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        text: String?,
        cursor: Cursor? = handCursor,
        font: Font? = fontTitleMini,
        background: Color? = Theme.btBg,
        foreground: Color? = DTII14,
        border: Border? = DTII4Border,
        backgroundEntered: Color? = DTII8,
        borderEntered: Border? = DTII4Border
    ) {
        setProperties(
            x,
            y,
            width,
            height,
            text,
            cursor,
            font,
            background,
            foreground,
            border,
            backgroundEntered,
            borderEntered
        )
    }

    constructor(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        text: String?,
        background: Color?,
        backgroundEntered: Color?,
        borderEntered: Border?,
        foreground: Color?
    ) {
        setProperties(
            x,
            y,
            width,
            height,
            text,
            handCursor,
            fontTitleMini,
            background,
            foreground,
            DTII4Border,
            backgroundEntered,
            borderEntered
        )
    }

    fun setProperties(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        text: String?,
        cursor: Cursor?,
        font: Font?,
        background: Color?,
        foreground: Color?,
        border: Border?,
        backgroundEntered: Color?,
        borderEntered: Border?
    ) {
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

    fun setProperties(x: Int, y: Int, width: Int, height: Int, cursor: Cursor?, background: Color?) {
        setLocation(x, y)
        setSize(width, height)
        setCursor(cursor)
        setBackground(background)
        isContentAreaFilled = true
        isFocusPainted = false
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

    /**
     * text button filled with icon
     */
    fun setProperties(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        cursor: Cursor?,
        background: Color?,
        isSolid: Boolean?,
        icon: Icon?
    ) {
        this.setProperties(x, y, icon, cursor)
        this.setSize(width, height)
        this.background = background
        this.isContentAreaFilled = isSolid!!
    }
}