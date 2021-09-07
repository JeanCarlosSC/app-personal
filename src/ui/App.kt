package ui

import Data
import lib.sRAD.kotlin.gui.component.VentanaEmergente
import lib.sRAD.kotlin.gui.sComponent.*
import numeroDeSemana
import javax.swing.JLabel
import java.time.LocalDate.now

class App: SFrame(1320, 704) {
    //ui
    private val pPrincipal = SPanel(196, 64, 900, 532, SPanel.EXTERNO)
    //components
    private val pResumen = SPanel(2, 2, 896, 496)
    private val pListas = object: SPanel(2, 2, 896, 496) {
        val lLista: SLabel
        val cListas: SComboBox
        val scroll: SScrollPane
        val pTasks: SPanel
        val bAdd: SButton

        init {
            lLista = SLabel(34, 32, 68, text="Lista")
            add(lLista)

            cListas = SComboBox(132, 32, 200, 32, Data.getListNames())
            add(cListas)

            pTasks = SPanel(2, 2, 594, 400)

            val wNewTask = object: VentanaEmergente(this@App, 500, 256) {
                init {
                    val lTask = SLabel(32, 32, 150, text = "Ingrese tarea")
                    add(lTask)

                    val taTask = STextArea(32, 94, 436, 68)
                    add(taTask)

                    val bAdd = SButton(134, 192, text = "Añadir")
                    bAdd.addActionListener {
                        Data.newTask(cListas.selectedIndex, taTask.text)
                        cerrar()
                        taTask.text = ""
                        updateContent()
                    }
                    add(bAdd)

                    val bClose = SButton(266, 192, text = "Cerrar")
                    bClose.addActionListener { cerrar() }
                    add(bClose)
                }
            }

            bAdd = SButton(364, 32, text = "+ Tarea")
            bAdd.addActionListener { wNewTask.lanzar() }
            add(bAdd)

            updateContent()

            scroll = SScrollPane(32, 96, 600, 400, pTasks)
            add(scroll)
        }

        fun updateContent() {
            pTasks.removeAll()

            val lista = Data.listas[cListas.selectedIndex].items
            for (i in 0 until lista.size) {
                val lTask = SLabel(32, 32+i*32, 500, text = lista[i])
                pTasks.add(lTask)
            }
            if(64+lista.size*32>396) {
                pTasks.setSize(pTasks.width, 64+lista.size*32)
            }
            else {
                pTasks.setSize(pTasks.width, 396)
            }

            //repaint
            pTasks.repaint()
        }
    }

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