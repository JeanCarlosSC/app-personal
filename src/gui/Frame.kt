package gui

import lib.sRAD.kotlin.gui.sComponent.SFrame

class Frame: SFrame(1320, 704) {

    private val nav = object: Navigation(64, 64) {
        override fun bResumenAction() {
            main.setResumen()
        }

        override fun bListasAction() {
            main.setListas()
        }
    }

    private val main = Main(this)

    init {
        setMainBar("App personal")

        add(nav)
        add(main)
    }

}