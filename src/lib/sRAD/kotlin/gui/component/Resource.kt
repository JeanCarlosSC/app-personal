package lib.sRAD.kotlin.gui.component

import java.awt.Color
import java.awt.Cursor
import java.awt.Font
import javax.swing.BorderFactory

//----------------------------------------COLOUR PALETTES---------------------------------------------------------------
//basic
@JvmField
val BLACK = Color(0, 0, 0)
val RED = Color(255, 0, 0)
@JvmField
val WHITE = Color(255, 255, 255)

//green complementary cake
val GCC1 = Color(122, 179, 102)
val GCC2 = Color(193, 255, 171)
val GCC3 = Color(212, 255, 196)
val GCC4 = Color(179, 84, 167)
val GCC5 = Color(255, 171, 245)

//azul gris celeste análogo
val AGCA1 = Color(74, 75, 246)
val AGCA2 = Color(63, 104, 212)
val AGCA3 = Color(82, 164, 235)
val AGCA4 = Color(63, 181, 212)
val AGCA5 = Color(74, 246, 235)

//monochrome dark blue
@JvmField
val MDB1 = Color(46, 49, 107)
val MDB2 = Color(78, 80, 107)
val MDB3 = Color(79, 84, 184)
val MDB4 = Color(101, 107, 235)
val MDB5 = Color(174, 177, 238)

//warning paleta
val WP1 = Color(72, 140, 3)
val WP2 = Color(132, 217, 4)
val WP3 = Color(242, 203, 5)
@JvmField
val WP4 = Color(242, 159, 5)
val WP5 = Color(242, 68, 5)

//special palette 1
val SP1 = Color(169, 105, 0)
val SP2 = Color(232, 145, 0)
val SP3 = Color(255, 225, 0)
@JvmField
val SP4 = Color(58, 117, 181)
val SP5 = Color(82, 125, 181)
val SP6 = Color(70, 147, 171)
val SP7 = Color(72, 206, 247)

//others and specials
val blackTransparent = Color(0f, 0f, 0f, 0.8f)
val transparentMustard = Color(1f, 1f, 0f, 0.5f)
val transparent = Color(0f, 0f, 0f, 0f)
val superLightGray = Color(120, 120, 120)
val megaDarkWhite = Color(180, 180, 180)
val semiDarkWhite = Color(210, 210, 210)

//--------------------------------------COLOR THEMES--------------------------------------------------------------------
//tema amigable
val TA1 = Color(184, 84, 80)
val TA2 = Color(108, 142, 191)
val TA3 = Color(214, 182, 86)
val TA4 = Color(255, 89, 89)
val TA5 = Color(248, 206, 204)
val TA6 = Color(218, 232, 252)
val TA7 = Color(255, 242, 204)

//Dark Theme based on Intellij Idea
val DTII1 = Color(49, 51, 53) //second background
val DTII2 = Color(43, 43, 43) //console background
val DTII3 = Color(50, 53, 55) //main background
val DTII4 = Color(60, 63, 65)
val DTII5 = Color(81, 81, 81) //mainToolbar bottom border
val DTII6 = Color(85, 85, 85) //console border
val DTII7 = Color(124, 123, 119) //comment font
val DTII8 = Color(114, 115, 122) //var names without use font
val DTII9 = Color(152, 118, 168) //var names in use font
val DTII10 = Color(106, 135, 89) //string font
val DTII11 = Color(98, 148, 82) //javadoc font
val DTII12 = Color(199, 118, 50) //reserve word font
val DTII13 = Color(104, 150, 186) //numbers font
val DTII14 = Color(187, 187, 187) //main color font

// -----------------------------------------FONT------------------------------------------------------------------------
//Windows standard (es windows porque funciona en windows, es decir, no se ha basado en fuentes del SO)
val fontTitle = Font("Gill Sans MT Condensed", Font.BOLD, 64)
val fontTitle1 = Font("Arial", Font.BOLD, 28)
val fontTitle2 = Font("Arial", Font.BOLD, 18)
@JvmField
val fontTitleMini = Font("Arial", Font.PLAIN, 14) //used in title of mainBar, buttons
val fontVersion = Font("Arial", Font.PLAIN, 14)
@JvmField
val fontText = Font("Arial", Font.PLAIN, 17)
val WSFS = Font("Arial", Font.ITALIC, 20) //windows standard font subtitle
val fontEcuation = Font("Arial Narrow", Font.ITALIC, 20)
val fontTextMini = Font("Arial Narrow", Font.PLAIN, 14)
val fontTextBig = Font("Arial", Font.PLAIN, 18)
val fontTextBold = Font("Calibri", Font.BOLD, 18)

//Linux standard
val LST = Font("Gill Sans MT Condensed", Font.BOLD, 40) //linux standard title
val LSS = Font("Arial", Font.ITALIC, 25) //linux standard subtitle
val fontTextLinux = Font("Liberation Serif", Font.PLAIN, 18)
val fontTextBoldLinux = Font("Liberation Serif", Font.BOLD, 18)

//----------------------------------------CURSORS-----------------------------------------------------------------------
//standard
val defaultCursor = Cursor(0)
val crosshairCursor = Cursor(1)
val textCursor = Cursor(2)
val waitCursor = Cursor(3)
val nResizeCursor = Cursor(8)
val eResizeCursor = Cursor(11)
@JvmField
val handCursor = Cursor(12)
val moveCursor = Cursor(13)

//----------------------------------------BORDER (B)----------------------------------------------------------------
//standard
val blackBorder = BorderFactory.createLineBorder(BLACK, 2, false)

//Dark Theme based on Intellij Idea
val transparentBorder = BorderFactory.createLineBorder(transparent, 2, false)
@JvmField
val blackBorderTransparent = BorderFactory.createLineBorder(blackTransparent, 2, false)
@JvmField
val DTII1Border = BorderFactory.createLineBorder(DTII3, 2, false)
val grayBorder = BorderFactory.createLineBorder(DTII7, 2, false)
@JvmField
val DTII4Border = BorderFactory.createLineBorder(DTII6, 2, false)
val DTII4BorderRounded = BorderFactory.createLineBorder(DTII6, 2, true)
val darkGrayBorder = BorderFactory.createLineBorder(DTII1, 2, false)

//tema amigable
val ta2Border = BorderFactory.createLineBorder(TA3, 2, false)
val ta4Border = BorderFactory.createLineBorder(TA1, 2, false)
val ta6Border = BorderFactory.createLineBorder(TA2, 2, false)

//special palette 1
val darkOcherBorder = BorderFactory.createLineBorder(SP1, 2, false)

//azul gris celeste análogo
val agca4Border = BorderFactory.createLineBorder(AGCA4, 2, false)

//warning paleta
val wp2Border = BorderFactory.createLineBorder(WP2, 2, false)