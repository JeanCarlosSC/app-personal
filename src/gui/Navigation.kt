package gui

import lib.sRAD.kotlin.gui.sComponent.SButton
import lib.sRAD.kotlin.gui.sComponent.SPanel

class Navigation(frame: Frame, x: Int, y: Int): SPanel(x, y, 164, 144, EXTERNO) {
    private val bResumen = SButton(32, 32, 100, 32, "Resumen")
    private val bListas = SButton(32, 82, 100, 32, "Listas")

    init {
        bResumen.addActionListener { frame.setResumen() }
        add(bResumen)

        bListas.addActionListener { frame.setListas() }
        add(bListas)
    }
}