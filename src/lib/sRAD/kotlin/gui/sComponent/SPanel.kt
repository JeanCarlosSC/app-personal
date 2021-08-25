package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.Theme.pB
import lib.sRAD.kotlin.gui.component.Theme.pBg
import java.awt.Color
import java.awt.Dimension
import java.awt.LayoutManager
import javax.swing.JPanel
import javax.swing.border.Border

open class SPanel: JPanel {

    /**
     * Crea un panel decorado con las dimensiones y posición dada.
     */
    constructor (x: Int, y: Int, width: Int, height: Int) {
        setProperties(x, y, width, height, pBg, null)
    }

    constructor (type: Int, x: Int, y: Int, width: Int, height: Int) {
        when (type) {
            INTERNO -> setProperties(x, y, width, height, pBg, null)
            EXTERNO -> setProperties(x, y, width, height, pBg, pB, null)
        }
    }

    constructor (x: Int, y: Int, width: Int, height: Int, background: Color?, border: Border?, layout: LayoutManager?) {
        setProperties(x, y, width, height, background, border, layout)
    }

    constructor()

    override fun setSize(width: Int, height: Int) {
        super.setSize(width, height)
        preferredSize = Dimension(width, height)
    }

    fun setProperties(x: Int, y: Int, width: Int, height: Int, background: Color?, layout: LayoutManager?) {
        setSize(width, height)
        setLocation(x, y)
        setBackground(background)
        setLayout(layout)
    }

    fun setProperties(x: Int, y: Int, width: Int, height: Int, background: Color?, border: Border?, layout: LayoutManager?) {
        setProperties(x, y, width, height, background, layout)
        setBorder(border)
    }

    companion object {
        //Formas pre-establecidas
        const val INTERNO: Int = 0 //panel interno de un scroll que no requiere borde
        const val EXTERNO: Int = 1 //panel independiente que requiere borde
    }

}
