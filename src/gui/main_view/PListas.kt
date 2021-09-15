package gui.main_view

import lib.sRAD.kotlin.gui.component.VentanaEmergente
import lib.sRAD.kotlin.gui.sComponent.*
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.DefaultComboBoxModel
import javax.swing.ImageIcon

class PListas(frame: SFrame): SPanel(2, 2, 894, 528) {
    private val lLista: SLabel
    private val cListas: SComboBox
    private val scroll: SScrollPane
    private val pTasks: SPanel
    private val bAddList: SButton
    private val bAddTask: SButton

    init {
        lLista = SLabel(34, 32, 68, text = "Lista")
        add(lLista)

        cListas = SComboBox(132, 32, 200, 32, Data.getListNames()) { updateContent() }
        add(cListas)

        pTasks = SPanel(2, 2, 594, 400)

        val wNewTask = object : VentanaEmergente(frame, 500, 256) {
            init {
                val lTask = SLabel(32, 32, 150, text = "Ingrese tarea")
                add(lTask)

                val taTask = STextArea(32, 94, 436, 68)
                add(taTask)

                val bAdd = SButton(134, 192, "Añadir") {
                    Data.newTask(cListas.selectedIndex, taTask.text)
                    cerrar()
                    taTask.text = ""
                    updateContent()
                }
                add(bAdd)

                val bClose = SButton(266, 192, "Cerrar") { cerrar() }
                add(bClose)
            }
        }

        bAddTask = SButton(496, 32, "New Task") { wNewTask.lanzar() }
        add(bAddTask)

        val wNewList = object : VentanaEmergente(frame, 500, 256) {
            init {
                val lList = SLabel(32, 32, 150, text = "Ingrese nombre de lista")
                add(lList)

                val taList = STextArea(32, 94, 436, 68)
                add(taList)

                val bAdd = SButton(134, 192, "Añadir") {
                    Data.newList(taList.text)
                    cerrar()
                    taList.text = ""
                    updateContent()
                }
                add(bAdd)

                val bClose = SButton(266, 192, "Cerrar") { cerrar() }
                add(bClose)
            }
        }

        bAddList = SButton(364, 32, "New list") { wNewList.lanzar() }
        add(bAddList)

        updateContent()

        scroll = SScrollPane(32, 96, 832, 404, pTasks)
        add(scroll)
    }

    fun updateContent() {
        //update panel tasks
        pTasks.removeAll()

        val lista = Data.listas[cListas.selectedIndex].items
        for (i in 0 until lista.size) {
            val lTrash = object: SButton(32, 16+i*32, ImageIcon("resources/trash_off.png")), MouseListener {
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
                    setLocation(32, 18+i*32)
                }

                override fun mouseExited(e: MouseEvent?) {
                    icon = trashOff
                    setLocation(32, 16+i*32)
                }
            }
            pTasks.add(lTrash)

            val lTask = SLabel(64, 16+i*32, 500, text = lista[i])
            pTasks.add(lTask)
        }

        //size
        if(32+lista.size*32>396) {
            pTasks.setSize(pTasks.width, 32+lista.size*32)
        }
        else {
            pTasks.setSize(pTasks.width, 396)
        }

        //update combo box list
        val selectedIndex = cListas.selectedIndex
        cListas.model = DefaultComboBoxModel<Any?>( Data.getListNames() )
        cListas.selectedIndex = selectedIndex

        //repaint
        repaint()
    }
}