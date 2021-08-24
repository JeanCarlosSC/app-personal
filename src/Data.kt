import java.time.LocalDate
import java.time.temporal.ChronoUnit

val ITN = Asignatura(0, 1, 2)

val inicio = LocalDate.of(2021, 8, 23)
val hoy = LocalDate.now()
val numeroDeSemana = (ChronoUnit.DAYS.between(inicio, hoy)).toInt()/7 + 1

class Asignatura(val autonomo: Int, val creditos: Int, val horasDeClase: Int) {
    fun getHorasAutonomas(): Int {
        return creditos*3 - horasDeClase
    }
    fun getHorasPendientes(): Int {
        return numeroDeSemana*getHorasAutonomas() - autonomo
    }
}
