package ui

import lib.sRAD.gui.sComponent.SFrame

class Window: SFrame() {
    init {
        extendedState = MAXIMIZED_BOTH
        setLocationRelativeTo(this)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }
}