package lib.sRAD.kotlin.logic.component

object Matriz {
    fun toString(matriz: Array<DoubleArray>): String {
        val toString = StringBuilder()
        for (i in matriz) {
            for (j in i) {
                toString.append("%12.10f ".formatted(j))
            }
            toString.append("\n")
        }
        return toString.toString()
    }

    fun toString(matriz: DoubleArray): String {
        val toString = StringBuilder()
        for (i in matriz) {
            toString.append("%12.10f ".formatted(i))
        }
        return toString.toString()
    }

    fun estadoEstable(matriz: Array<DoubleArray>): DoubleArray {
        val estado = DoubleArray(matriz.size)
        estado[0] = 1.0
        return estabilizar(estado, matriz)
    }

    private fun estabilizar(estado: DoubleArray, matriz: Array<DoubleArray>): DoubleArray {
        val resultado = multiplicar(estado, matriz)
        for (i in estado.indices) {
            if (estado[i] != resultado[i]) {
                return estabilizar(resultado, matriz)
            }
        }
        return estado
    }

    fun multiplicar(vector: DoubleArray, matriz: Array<DoubleArray>): DoubleArray {
        val resultado = DoubleArray(vector.size)
        for (j in 0 until matriz[0].size) {
            for (i in matriz.indices) {
                resultado[j] += vector[i] * matriz[i][j]
            }
        }
        return resultado
    }


    fun calcularTiempos(estadoEstable: DoubleArray, transicion: Array<DoubleArray>): Array<DoubleArray>? {
        val size = estadoEstable.size
        val tiempos = Array(size) { DoubleArray(size) }
        //por cada columna establece un sistema de ecuaciones
        for (col in 0 until size) {
            val sistema = Array(size - 1) {
                DoubleArray(
                    size
                )
            }
            for (i in 0 until size) {
                for (j in 0 until size) {
                    if (j != col && i != col) {
                        val colSistema = if (j < col) j else j - 1
                        val filSistema = if (i < col) i else i - 1
                        sistema[filSistema][colSistema] = transicion[i][j] * -1
                        if (filSistema == colSistema) {
                            sistema[filSistema][colSistema] += 1.0
                        }
                    }
                }
                if (i < size - 1) sistema[i][size - 1] = 1.0
            }
            //resuelve el sistema
            val resultados = resolverSistemaLineal(sistema)
            //guarda los resultados de los calculos en la matriz de tiempos
            for (i in 0 until size) {
                if (i == col) {
                    tiempos[i][col] = 1 / estadoEstable[col]
                } else {
                    if (i < col) {
                        tiempos[i][col] = resultados!![i]
                    } else {
                        tiempos[i][col] = resultados!![i - 1]
                    }
                }
            }
        }
        return tiempos
    }

    fun resolverSistemaLineal(sistema: Array<DoubleArray>): DoubleArray? {
        return try {
            // k is the index of row with max pivot
            var k: Int
            // swapTemp is the temporary row swapping variable
            var swapTemp: DoubleArray
            for (i in sistema.indices) {
                k = i
                for (j in i + 1 until sistema.size) {
                    if (sistema[j][i] > sistema[k][i]) {
                        k = j
                    }
                }
                if (sistema[k][i] == 0.0) {
                    // Mark this column as free column
                    // Will solve later -> not now
                    println("\n----- Error 005 -----")
                    println("System of equations contains free variables")
                    System.exit(0)
                }

                // Swap the rows i and k
                swapTemp = sistema[i]
                sistema[i] = sistema[k]
                sistema[k] = swapTemp

                // Iterate over all subsequent rows
                for (j in sistema.indices) {
                    // Reduce row by row
                    if (i != j && !reduceRow(sistema[i], sistema[j], i)) {
                        println("\n----- Error 006 -----")
                        println("System of equations cannot be solved by this program")
                        System.exit(0)
                    }
                }
            }
            //convierte a los pivotes en 1
            for (i in sistema.indices) {
                val valor = sistema[i][i]
                sistema[i][i] /= valor
                sistema[i][sistema.size] /= valor
            }
            val resultado = DoubleArray(sistema.size)
            for (i in sistema.indices) {
                resultado[i] = sistema[i][sistema.size]
            }
            resultado
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }

    private fun reduceRow(r1: DoubleArray, r2: DoubleArray, pos: Int): Boolean {
        try {
            if (r1.size != r2.size) return false
            val factor = r2[pos] / r1[pos]
            for (i in pos until r1.size) {
                r2[i] -= factor * r1[i]
            }
            return true
        } catch (e: ArrayIndexOutOfBoundsException) {
            println("\n----- Error 004 -----")
            println("Array index out of range in function reduceRow()")
        }
        return false
    }
}