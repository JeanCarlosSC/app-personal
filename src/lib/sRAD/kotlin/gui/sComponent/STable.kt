package lib.sRAD.kotlin.gui.sComponent

import lib.sRAD.kotlin.gui.component.*
import lib.sRAD.kotlin.gui.sComponent.SScrollPane.Companion.customScroll
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import java.util.*
import javax.swing.JLabel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.border.Border
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList

class STable @JvmOverloads constructor(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    matriz: ArrayList<ArrayList<String>>,
    cellWidth: Int = 200,
    cellHeight: Int = 30
) : JScrollPane() {
    fun setProperties(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        matriz: ArrayList<ArrayList<String>>,
        cellWidth: Int,
        cellHeight: Int
    ) {
        val placeholdes = Vector<String>()
        for (element in matriz[0]) {
            placeholdes.add(element)
        }
        val modelo = DefaultTableModel()
        modelo.setColumnIdentifiers(placeholdes)
        for (i in 1 until matriz.size) {
            modelo.addRow(Vector<Any>())
            for (j in matriz[0].indices) {
                modelo.setValueAt(matriz[i][j], i - 1, j)
            }
        }
        val tabla = JTable()
        setProperties(tabla, modelo, cellHeight)
        tabla.autoResizeMode = JTable.AUTO_RESIZE_OFF
        tabla.rowHeight = cellHeight
        for (i in matriz[0].indices) {
            tabla.columnModel.getColumn(i).preferredWidth = cellWidth
            tabla.columnModel.getColumn(i).maxWidth = cellWidth
            tabla.columnModel.getColumn(i).minWidth = cellWidth
        }
        tabla.setSize(cellWidth * matriz[0].size, cellHeight * matriz.size)
        tabla.preferredSize = Dimension(cellWidth * matriz[0].size, cellHeight * (matriz.size - 1))
        val header = tabla.tableHeader
        header.background = DTII6
        header.reorderingAllowed = false
        header.setSize(cellWidth * matriz[0].size, 30)
        header.preferredSize = Dimension(cellWidth * matriz[0].size, 30)
        header.defaultRenderer = getCustomTable(DTII6, null, null, WHITE, fontText)
        setViewportView(tabla)
        setLocation(x, y)
        setSize(width, height)
        background = DTII6
        viewport.background = DTII3
        border = DTII4Border
        verticalScrollBar.setUI(customScroll)
        horizontalScrollBar.setUI(customScroll)
    }

    fun getPanelBar(table: JTable?, x: Int, y: Int, width: Int, height: Int): JScrollPane {
        return getPanelBar(table, x, y, width, height, DTII3, null)
    }

    fun getPanelBar(
        table: JTable?,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        background: Color?,
        border: Border?
    ): JScrollPane {
        val panelScroll = JScrollPane(table)
        panelScroll.setLocation(x, y)
        panelScroll.setSize(width, height)
        panelScroll.viewport.background = background
        panelScroll.border = border
        return panelScroll
    }

    fun setProperties(table: JTable, modelo: DefaultTableModel?) {
        setProperties(table, modelo, DTII6, 40)
    }

    fun setProperties(table: JTable, modelo: DefaultTableModel?, rowHeight: Int) {
        setProperties(table, modelo, DTII6, rowHeight)
    }

    fun setProperties(table: JTable, modelo: DefaultTableModel?, gridColor: Color?, rowHeight: Int) {
        table.model = modelo
        table.rowHeight = rowHeight
        table.setDefaultRenderer(Any::class.java, customTable)
        table.gridColor = gridColor
    }

    companion object {
        //advanced graphic builder
        val customTable: DefaultTableCellRenderer
            get() = getCustomTable(DTII3, DTII3, MDB1, DTII14, fontText)

        fun getCustomTable(
            colorPrincipal: Color?, colorSecundario: Color?, colorSeleccion: Color?, colorFuente: Color?, fuente: Font?
        ): DefaultTableCellRenderer {
            return object : DefaultTableCellRenderer() {
                override fun getTableCellRendererComponent(
                    table: JTable, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int
                ): Component {
                    val celda =
                        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column) as JLabel
                    celda.isOpaque = true
                    celda.font = fuente
                    celda.foreground = colorFuente
                    celda.horizontalAlignment = CENTER
                    if (row % 2 != 0) {
                        celda.background = colorPrincipal
                    } else {
                        celda.background = colorSecundario
                    }
                    if (isSelected) {
                        celda.background = colorSeleccion
                        celda.foreground = WHITE
                    }
                    return celda
                }
            }
        }
    }

    init {
        setProperties(x, y, width, height, matriz, cellWidth, cellHeight)
    }
}