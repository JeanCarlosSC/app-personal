package ui

import Data
import lib.sRAD.kotlin.gui.sComponent.SButton
import lib.sRAD.kotlin.gui.sComponent.SFrame
import lib.sRAD.kotlin.gui.sComponent.SLabel
import lib.sRAD.kotlin.gui.sComponent.SPanel
import numeroDeSemana
import javax.swing.JLabel
import java.time.LocalDate.now

class App: SFrame(1320, 704) {
    val data = Data()
    val pPrincipal = SPanel(164, 64, 900, 500, SPanel.EXTERNO)

    init {
        //initial frame properties
        setMainBar("App personal")

        initComponents()
        createNav()
        add(pPrincipal)

        //final frame properties
        layout = null
        setLocationRelativeTo(this)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }

    fun createNav() {
        val listas = SButton(32, 64, 100, 32, "Listas")
        listas.addActionListener { 0 }
        add(listas)
    }

    fun initComponents() {
        initPPrincipal()
    }

    fun initPPrincipal() {
        //header
        val lFecha = SLabel(32, 32, 700, 32, "${now()}: semana $numeroDeSemana.")
        lFecha.horizontalAlignment = JLabel.CENTER
        pPrincipal.add(lFecha)

        //asignaturas
        for (i in 0 until data.asignaturas.size) {
            val lAsignatura = SLabel(96, 64 + 32*i, 600, 32, "${data.asignaturas[i]}")
            pPrincipal.add(lAsignatura)

            val btPlus = SButton(32, 66 + 32*i, 28, 28, "+")
            btPlus.addActionListener {
                data.asignaturas[i].autonomo++
                lAsignatura.text = data.asignaturas[i].toString()
                repaint()
                data.save()
            }
            pPrincipal.add(btPlus)
        }
    }
}