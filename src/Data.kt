import java.io.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

val inicio: LocalDate = LocalDate.of(2021, 8, 23)
val hoy: LocalDate = LocalDate.now()
val numeroDeSemana = (ChronoUnit.DAYS.between(inicio, hoy)).toInt()/7 + 1

object Data {
    val asignaturas = mutableListOf<Asignatura>()

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
    }

    fun save() {
        val salida = ObjectOutputStream(FileOutputStream("data/autonomo.ser"))
        for(i in 0 until 7) {
            salida.writeObject(asignaturas[i].autonomo)
        }
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
