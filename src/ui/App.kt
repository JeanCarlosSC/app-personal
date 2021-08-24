package ui

import Data
import numeroDeSemana
import javax.swing.JFrame
import javax.swing.JLabel
import java.time.LocalDate.now
import javax.swing.JButton

class App: JFrame() {
    init {
        //load data
        val data = Data()

        //initial frame properties
        extendedState = MAXIMIZED_BOTH
        setSize(1320, 704)

        //header
        val lFecha = JLabel()
        lFecha.text = "${now()}: semana $numeroDeSemana."
        lFecha.horizontalAlignment = JLabel.CENTER
        lFecha.setSize(500, 32)
        lFecha.setLocation(width/2-lFecha.width/2, 32)
        add(lFecha)

        //asignaturas
        for (i in 0 until data.asignaturas.size) {
            val lAsignatura = JLabel("${data.asignaturas[i]}")
            lAsignatura.setSize(500, 32)
            lAsignatura.setLocation(500, 64+i*32)
            add(lAsignatura)

            val btPlus = JButton("+")
            btPlus.addActionListener {
                data.asignaturas[i].autonomo ++
                lAsignatura.text = data.asignaturas[i].toString()
                repaint()
                data.save()
            }
            btPlus.setSize(28, 28)
            btPlus.setLocation(460, 66+i*32)
            add(btPlus)
        }

        //final frame properties
        layout = null
        setLocationRelativeTo(this)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }
}