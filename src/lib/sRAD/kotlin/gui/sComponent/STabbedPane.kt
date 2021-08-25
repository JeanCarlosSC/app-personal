package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.Theme.tpBg
import lib.sRAD.kotlin.gui.component.Theme.tpFg
import java.awt.Color
import javax.swing.JTabbedPane

class STabbedPane(type: Int, x: Int, y: Int, width: Int, height: Int) : JTabbedPane() {
    fun setProperties(x: Int, y: Int, width: Int, height: Int, background: Color?, foreground: Color?) {
        setSize(width, height)
        setLocation(x, y)
        setBackground(background)
        setForeground(foreground)
    }

    companion object {
        //configuraciones pre-establecidas
        const val DECORADO = 1 //se basa en los colores del tema actual
    }

    init {
        if (type == DECORADO) {
            setProperties(x, y, width, height, tpBg, tpFg)
        }
    }
}