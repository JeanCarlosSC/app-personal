package gui

import lib.sRAD.kotlin.gui.sComponent.SFrame

class Frame: SFrame(1320, 704) {

    private val nav = Navigation(this, 64, 64)
    private val main = Main(this)

    init {
        setMainBar("App personal")

        add(nav)
        add(main)
    }

    fun setResumen() {
        main.setResumen()
    }

    fun setListas() {
        main.setListas()
    }

}