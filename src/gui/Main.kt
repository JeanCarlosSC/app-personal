package gui

import gui.main_view.PListas
import lib.sRAD.kotlin.gui.sComponent.*

class Main(frame: SFrame): SPanel(260, 64, 900, 532, EXTERNO) {

    private val pResumen = SPanel(2, 2, 896, 496)
    private val pListas = PListas(frame)

    init {
        add(pResumen)
    }

    private fun setPanel(panel: SPanel) {
        removeAll()
        add(panel)
        repaint()
    }

    fun setResumen() {
        setPanel(pResumen)
    }

    fun setListas() {
        setPanel(pListas)
    }

}