package ui

import ITN
import javax.swing.JFrame
import javax.swing.JLabel
import java.time.LocalDate.now

class App: JFrame() {
    init {
        extendedState = MAXIMIZED_BOTH
        setSize(1320, 704)

        val lFecha = JLabel()
        lFecha.text = "${now()}"
        lFecha.horizontalAlignment = JLabel.CENTER
        lFecha.setSize(500, 32)
        lFecha.setLocation(width/2-lFecha.width/2, 32)
        add(lFecha)

        val lITN = JLabel("Introducción a la teoría de números, autónomo: ${ITN.getHorasAutonomas()} restante: ${ITN.getHorasPendientes()}")
        lITN.horizontalAlignment = JLabel.CENTER
        lITN.setSize(500, 32)
        lITN.setLocation(width/2-lITN.width/2, 64)
        add(lITN)

        layout = null
        setLocationRelativeTo(this)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }
}