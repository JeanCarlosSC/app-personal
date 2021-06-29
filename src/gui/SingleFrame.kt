package gui

import lib.sRAD.gui.sComponent.SFrame
import lib.sRAD.gui.sComponent.SPanel

class SingleFrame: SFrame(1280, 720) {
    private val container = SPanel(160, 29, 960, 687)
    init {
        setMainBar("Quick Desktop")
        add(container)
    }
}