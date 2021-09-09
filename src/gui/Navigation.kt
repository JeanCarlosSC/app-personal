package gui

import lib.sRAD.kotlin.gui.sComponent.SButton
import lib.sRAD.kotlin.gui.sComponent.SPanel

abstract class Navigation(x: Int, y: Int): SPanel(x, y, 164, 144, EXTERNO) {

    private val bResumen = SButton(32, 32, "Resumen") { bResumenAction() }

    private val bListas = SButton(32, 82, "Listas") { bListasAction() }

    init {
        this.add(bResumen)
        this.add(bListas)
    }

    abstract fun bResumenAction()

    abstract fun bListasAction()

}