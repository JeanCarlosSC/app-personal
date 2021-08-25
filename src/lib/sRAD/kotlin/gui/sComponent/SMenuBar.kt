package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.DTII14
import lib.sRAD.kotlin.gui.component.DTII1Border
import lib.sRAD.kotlin.gui.component.DTII3
import java.awt.Color
import java.awt.Component
import java.awt.Graphics
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.border.Border

class SMenuBar @JvmOverloads constructor(
    x: Int = 0,
    y: Int = 29,
    width: Int = 1280,
    height: Int = 28,
    private val generalBackground: Color = DTII3,
    private val generalForeground: Color = DTII14,
    private val generalBorder: Border = DTII1Border
) : JMenuBar() {
    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        for (menuElement in subElements) {
            val menu = menuElement.component as JMenu
            changeComponentColors(menu)
            menu.isOpaque = true
            val menuElements = menu.subElements
            for (popupMenuElement in menuElements) {
                val popupMenu = popupMenuElement.component as JPopupMenu
                popupMenu.border = null
                val menuItems = popupMenuElement.subElements
                for (menuItemElement in menuItems) {
                    val menuItem = menuItemElement.component as JMenuItem
                    changeComponentColors(menuItem)
                    menuItem.isOpaque = true
                }
            }
        }
    }

    private fun changeComponentColors(comp: Component) {
        comp.background = generalBackground
        comp.foreground = generalForeground
    }

    init {
        background = generalBackground
        border = generalBorder
        this.setBounds(x, y, width, height)
    }
}