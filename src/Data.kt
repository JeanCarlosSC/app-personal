import java.io.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

val inicio: LocalDate = LocalDate.of(2021, 8, 23)
val hoy: LocalDate = LocalDate.now()
val numeroDeSemana = (ChronoUnit.DAYS.between(inicio, hoy)).toInt()/7 + 1

object Data {
    val asignaturas = mutableListOf<Asignatura>()
    private val listas = mutableListOf<Lista>()

    init {
        //informacion por defecto
        asignaturas.add(Asignatura("Análisis de sistemas", 0, 2, 4))
        asignaturas.add(Asignatura("Probabilidad", 0, 3, 4))
        asignaturas.add(Asignatura("Expresión musical secuencializada", 0, 1, 2))
        asignaturas.add(Asignatura("Introducción a la teoría de números", 0, 1, 2))
        asignaturas.add(Asignatura("Ciencias de la computación 3", 0, 1, 2))
        asignaturas.add(Asignatura("Segunda lengua 1", 0, 2, 4))
        asignaturas.add(Asignatura("Liderazgo y desarrollo profesional", 0, 2, 4))

        load()
    }

    private fun load() {
        val path = File("data/autonomo.ser")
        if(path.exists() && path.isFile) {
            val entrada = ObjectInputStream(FileInputStream("data/autonomo.ser"))
            for (i in 0 until 7) {
                asignaturas[i].autonomo = entrada.readObject() as Int
            }
        }
        loadLists()
    }

    fun save() {
        val salida = ObjectOutputStream(FileOutputStream("data/autonomo.ser"))
        for(i in 0 until 7) {
            salida.writeObject(asignaturas[i].autonomo)
        }
        saveLists()
    }

    private fun loadLists() {
        val path = File("data/listas.ser")
        if(path.exists() && path.isFile) {
            val entrada = ObjectInputStream(FileInputStream("data/listas.ser"))
            val numListas = entrada.readObject() as Int
            for (i in 0 until numListas) {
                val numItems = entrada.readObject() as Int
                listas.add(Lista(entrada.readObject() as String))
                for (j in 0 until numItems) {
                    listas[j].add(entrada.readObject() as String)
                }
            }
        }
        else {
            listas.add(Lista("default"))
        }
    }

    private fun saveLists() {
        val salida = ObjectOutputStream(FileOutputStream("data/listas.ser"))
        salida.writeObject(listas.size)
        for(lista in listas) {
            salida.writeObject(lista.getSize())
            salida.writeObject(lista.nombre)
            for (item in lista.getItems()) {
                salida.writeObject(item)
            }
        }
    }

    fun getListNames(): Array<String?> {
        val listNames: Array<String?> = arrayOfNulls(listas.size)
        for (i in 0 until listas.size) {
            listNames[i] = listas[i].nombre
        }
        return listNames
    }
}

class Lista(var nombre: String) {
    private val items = mutableListOf<String>()

    fun add(item: String) {
        items.add(item)
    }

    fun getSize(): Int {
        return items.size
    }

    fun getItems(): MutableList<String> {
        return items
    }
}

class Asignatura(private val nombre: String, var autonomo: Int, private val creditos: Int, private val horasDeClase: Int) {
    private fun getHorasAutonomas(): Int {
        return creditos*3 - horasDeClase
    }
    private fun getHorasPendientes(): Int {
        return numeroDeSemana*getHorasAutonomas() - autonomo
    }
    override fun toString(): String {
        val horasPendientes = getHorasPendientes()
        return "$nombre: $horasPendientes hora${if(horasPendientes != 1)"s" else ""} pendiente${if(horasPendientes != 1)"s" else ""}."
    }
}
