package gui

import lib.sRAD.kotlin.gui.component.VentanaEmergente
import lib.sRAD.kotlin.gui.sComponent.*
import numeroDeSemana
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.time.LocalDate
import javax.swing.ImageIcon
import javax.swing.JLabel

class Frame: SFrame(1320, 704) {
    private val pPrincipal = SPanel(196, 64, 900, 532, SPanel.EXTERNO)
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

            val wNewTask = object: VentanaEmergente(this@Frame, 500, 256) {
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

            scroll = SScrollPane(32, 96, 832, 400, pTasks)
            add(scroll)
        }

        fun updateContent() {
            pTasks.removeAll()

            val lista = Data.listas[cListas.selectedIndex].items
            for (i in 0 until lista.size) {
                val lTrash = object: SButton(32, 32+i*32, ImageIcon("resources/trash_off.png")), MouseListener {
                    val trashOn = ImageIcon("resources/trash_on.png")
                    val trashOff = ImageIcon("resources/trash_off.png")

                    init {
                        addMouseListener(this)
                    }

                    override fun mouseClicked(e: MouseEvent?) {  }

                    override fun mousePressed(e: MouseEvent?) {
                        Data.removeTask(cListas.selectedIndex, i)
                        updateContent()
                    }

                    override fun mouseReleased(e: MouseEvent?) {  }

                    override fun mouseEntered(e: MouseEvent?) {
                        icon = trashOn
                        setLocation(32, 34+i*32)
                    }

                    override fun mouseExited(e: MouseEvent?) {
                        icon = trashOff
                        setLocation(32, 32+i*32)
                    }
                }
                pTasks.add(lTrash)

                val lTask = SLabel(64, 32+i*32, 500, text = lista[i])
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
        setMainBar("App personal")

        initComponents()
        createNav()

        add(pPrincipal)
    }

    private fun createNav() {
        val bResumen = SButton(64, 64, 100, 32, "Resumen")
        bResumen.addActionListener { setResumen() }
        add(bResumen)

        val bListas = SButton(64, 114, 100, 32, "Listas")
        bListas.addActionListener { setListas() }
        add(bListas)
    }

    private fun initPPrincipal() {
        pPrincipal.add(pResumen)
    }

    private fun setPanel(panel: SPanel) {
        pPrincipal.removeAll()
        pPrincipal.add(panel)
        pPrincipal.repaint()
    }

    private fun initComponents() {
        initPResumen()
        initPPrincipal()
    }

    private fun initPResumen() {
        //header
        val lFecha = SLabel(32, 32, 700, 32, "${LocalDate.now()}: semana $numeroDeSemana.")
        lFecha.horizontalAlignment = JLabel.CENTER
        pResumen.add(lFecha)
    }

    private fun setResumen() {
        setPanel(pResumen)
    }

    private fun setListas() {
        setPanel(pListas)
    }

}