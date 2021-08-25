package lib.sRAD.kotlin.gui.component

import lib.sRAD.kotlin.gui.component.*
import java.awt.Color
import javax.swing.border.Border

/**
 * bg = background
 */
object Theme {
    //general
    @JvmField
    var bg2: Color = DTII1

    //frame
    var fBg: Color = DTII2

    //button
    @JvmField
    var btBg: Color = DTII5

    //ventana emergente
    var veBg: Color = DTII4

    //panel
    @JvmField
    var pBg: Color = DTII3
    @JvmField
    var pB: Border = DTII4Border //border

    //font
    @JvmField
    var fFg: Color = DTII14 //foreground text

    //main bar
    var mbFg: Color = DTII7 //foreground

    //tabbed pane
    @JvmField
    var tpBg: Color = WP4
    @JvmField
    var tpFg: Color = BLACK // foreground
}