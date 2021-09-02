package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.MainBar
import lib.sRAD.kotlin.gui.component.Theme
import lib.sRAD.kotlin.gui.component.blackBorderTransparent
import java.awt.Color
import java.awt.Component
import java.awt.LayoutManager
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.border.Border

open class SFrame(width: Int = 1280, height: Int = 720, background: Color = Theme.fBg, undecorated: Boolean = true,
                  border: Border = blackBorderTransparent, relativeLocation: Component? = null, visible: Boolean = false,
                  defaultCloseOperation: Int = EXIT_ON_CLOSE, layout: LayoutManager? = null) : JFrame() {

    init  {
        setProperties(width, height, background, undecorated, border, relativeLocation, visible, defaultCloseOperation, layout)
    }

    fun setProperties(width: Int, height: Int, background: Color, undecorated: Boolean, border: Border, relativeLocation: Component?,
                      visible: Boolean, defaultCloseOperation: Int, layout: LayoutManager?) {
        setSize(width, height)
        contentPane.background = background
        isUndecorated = undecorated
        rootPane.border = border
        setLocationRelativeTo(relativeLocation)
        setDefaultCloseOperation(defaultCloseOperation)
        setLayout(layout)
        isVisible = visible
    }

    fun setMainBar(title: String) {
        setMainBar(title, "resources/sRAD/exampleLogo.png")
    }

    fun setMainBar(title: String, pathLogo: String) {
        val mainBar = if(width>100) {
            MainBar(this, width)
        }
        else {
            MainBar(this)
        }
        mainBar.setTitle(title)
        mainBar.setLogo(ImageIcon(pathLogo))
        add(mainBar)
        repaint()
    }

}