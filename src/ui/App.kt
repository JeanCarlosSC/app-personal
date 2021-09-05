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
    //ui
    private val pPrincipal = SPanel(196, 64, 900, 500, SPanel.EXTERNO)
    //components
    private val pResumen = SPanel(2, 2, 896, 496)
    private val pListas = SPanel(2, 2, 896, 496)

    init {
        //frame properties
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

    private fun createNav() {
        val bResumen = SButton(64, 64, 100, 32, "Resumen")
        bResumen.addActionListener { setResumen() }
        add(bResumen)

        val bListas = SButton(64, 114, 100, 32, "Listas")
        bListas.addActionListener { setListas() }
        add(bListas)
    }

    private fun initComponents() {
        initPResumen()
        initPPrincipal()
    }

    private fun initPResumen() {
        //header
        val lFecha = SLabel(32, 32, 700, 32, "${now()}: semana $numeroDeSemana.")
        lFecha.horizontalAlignment = JLabel.CENTER
        pResumen.add(lFecha)

        //asignaturas
        for (i in 0 until Data.asignaturas.size) {
            val lAsignatura = SLabel(96, 64 + 32*i, 600, 32, "${Data.asignaturas[i]}")
            pResumen.add(lAsignatura)

            val btPlus = SButton(32, 66 + 32*i, 28, 28, "+")
            btPlus.addActionListener {
                Data.asignaturas[i].autonomo++
                lAsignatura.text = Data.asignaturas[i].toString()
                repaint()
                Data.save()
            }
            pResumen.add(btPlus)
        }
    }

    private fun initPPrincipal() {
        pPrincipal.add(pResumen)
    }

    private fun setPanel(panel: SPanel) {
        pPrincipal.removeAll()
        pPrincipal.add(panel)
        pPrincipal.repaint()
    }

    private fun setResumen() {
        setPanel(pResumen)
    }

    private fun setListas() {
        setPanel(pListas)
    }
}