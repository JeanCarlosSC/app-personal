package gui

import lib.sRAD.gui.component.VentanaEmergente
import lib.sRAD.gui.sComponent.*
import java.io.*

object SingleFrame: SFrame(1280, 720) {
    @JvmStatic
    fun iniciar() {
        SingleFrame.repaint()
    }

    private val contenedor = SPanel(SPanel.EXTERNO, 160, 61, 960, 671)
    private val bInicio = object: SButton(160, 29, 100, 32, "Inicio") {
        init {
            addActionListener { contenedor.set(PInicio) }
        }
    }
    private val bLectura = object: SButton(260, 29, 100, 32, "Lectura") {
        init {
            addActionListener { contenedor.set(PLectura) }
        }
    }
    init {
        setMainBar("Quick Desktop")

        add(bInicio)
        add(bLectura)

        contenedor.set(PInicio)
        add(contenedor)

        repaint()
    }
}

private fun SPanel.set(panel: SPanel) {
    removeAll()
    add(panel)
    repaint()
}

object PInicio: SPanel(2, 2, 956, 667)

object PLectura: SPanel(2, 2, 956, 667) {
    private val libros = mutableListOf<Libro>()
    private val bAddBook = object: SButton(0, 0, 32, 32, "+") {
        init {
            addActionListener {
                val pdfName = STextField(170, 32, 200,32)
                val bAdd = SButton(80, 80, 100, 32, "Añadir")
                val bCancelar = SButton(212, 80, 100, 32, "Cancelar")
                val ventanaEmergente = VentanaEmergente(SingleFrame,400,150)

                bAdd.addActionListener {
                    libros.add(Libro(pdfName.text, "0"))
                    guardar()
                    ventanaEmergente.cerrar()
                }
                bCancelar.addActionListener { ventanaEmergente.cerrar() }

                ventanaEmergente.add(SLabel(34, 32, 150, 28, "Nombre del pdf:"))
                ventanaEmergente.add(pdfName)
                ventanaEmergente.add(bAdd)
                ventanaEmergente.add(bCancelar)
                ventanaEmergente.lanzar()
            }
        }
    }

    init {
        cargar()
        add(bAddBook)
    }

    fun update() {
        removeAll()

        add(bAddBook)
        if(libros.size > 0) {
            for(i in 0 until libros.size) {
                val bAjustar = SButton(700, 62+64*i,100,32,"Ajustar")
                val bDelete = SButton(832, 62+64*i,100,32,"Eliminar")

                bAjustar.addActionListener {
                    val pdfName = STextField(170, 32, 200,32, libros[i].name)
                    val pdfPage = STextField(170, 64, 200,32, libros[i].page)
                    val bActualizar = SButton(80, 112, 100, 32, "Actualizar")
                    val bCancelar = SButton(212, 112, 100, 32, "Cancelar")
                    val ventanaEmergente = VentanaEmergente(SingleFrame,400,182)

                    bActualizar.addActionListener {
                        libros[i].name = pdfName.text
                        libros[i].page = pdfPage.text
                        guardar()
                        ventanaEmergente.cerrar()
                    }
                    bCancelar.addActionListener { ventanaEmergente.cerrar() }

                    ventanaEmergente.add(SLabel(34, 32, 150, 28, "Nombre del pdf:"))
                    ventanaEmergente.add(SLabel(34, 64, 150, 28, "Página del pdf:"))
                    ventanaEmergente.add(pdfName)
                    ventanaEmergente.add(pdfPage)
                    ventanaEmergente.add(bActualizar)
                    ventanaEmergente.add(bCancelar)
                    ventanaEmergente.lanzar()
                }
                bDelete.addActionListener {
                    libros.removeAt(i)
                    guardar()
                }

                add(SLabel(32, 64+64*i,400, 28, libros[i].name))
                add(SLabel(432, 64+64*i,268, 28, libros[i].page))
                add(bAjustar)
                add(bDelete)
            }
        }

        repaint()
    }
    fun cargar() {
        val path = File("data/lectura.ser")
        if(path.exists() && path.isFile) {
            val entrada = ObjectInputStream(FileInputStream("data/lectura.ser"))
            val pdfs = entrada.readObject().toString().toInt()
            if (pdfs > 0) {
                for (i in 0 until pdfs) {
                    libros.add(Libro(entrada.readObject() as String, entrada.readObject() as String))
                }
            }
        }
        update()
    }
    fun guardar() {
        val salida = ObjectOutputStream(FileOutputStream("data/lectura.ser"))
        salida.writeObject(libros.size.toString())
        if(libros.isNotEmpty()) {
            for(libro in libros) {
                salida.writeObject(libro.name)
                salida.writeObject(libro.page)
            }
        }
        update()
    }
}

class Libro(var name: String, var page: String)