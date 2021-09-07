import java.io.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

val inicio: LocalDate = LocalDate.of(2021, 8, 23)
val hoy: LocalDate = LocalDate.now()
val numeroDeSemana = (ChronoUnit.DAYS.between(inicio, hoy)).toInt()/7 + 1

object Data {
    val listas = mutableListOf<Lista>()

    init {
        load()
    }

    private fun load() {
        loadLists()
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
                    listas[i].add(entrada.readObject() as String)
                }
            }
        }
        else {
            listas.add(Lista("default"))
        }
    }

    private fun saveLists() {
        // verifica existencia del archivo y la carpeta
        val file = File("data/listas.ser")
        if (!file.exists()) {
            val folder = File("data")
            if(!folder.exists()) {
                folder.mkdir()
            }
            file.createNewFile()
        }
        // guarda la informacion
        val salida = ObjectOutputStream(FileOutputStream("data/listas.ser"))
        salida.writeObject(listas.size)
        for(lista in listas) {
            salida.writeObject(lista.items.size)
            salida.writeObject(lista.nombre)
            for (item in lista.items) {
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

    fun newTask(selectedIndex: Int, task: String) {
        listas[selectedIndex].add(task)
        saveLists()
    }
}

class Lista(var nombre: String) {
    val items = mutableListOf<String>()

    fun add(item: String) {
        items.add(item)
    }
}
