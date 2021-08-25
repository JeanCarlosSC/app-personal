package lib.sRAD.logic

import java.lang.NumberFormatException

object Extension {
    fun isInt(string: String): Boolean {
        return try {
            string.toInt()
            true
        } catch (numberFormat: NumberFormatException) {
            false
        }
    }

    fun isNumber(character: Char): Boolean {
        return character == '0' || character == '1' || character == '2' || character == '3' || character == '4' || character == '5' || character == '6' || character == '7' || character == '8' || character == '9'
    }

    fun isOperator(character: Char): Boolean {
        return character == '+' || character == '-' || character == '*' || character == '^' || character == '/'
    }

    fun toCOP(integer: Int): String {
        var str = ""
        var num = integer
        var ajusteModulo = 0
        if (num < 0) {
            str += "-"
            num *= -1
            ajusteModulo++
        }
        str += "$"
        var number = Integer.toString(num)
        while (!number.isEmpty()) {
            if (number.length % 3 == 0 && str.length != 1 + ajusteModulo) {
                str += "." + number[0]
            } else {
                str += number[0]
            }
            number = number.substring(1)
        }
        return str
    }

    fun toCOP(valor: Double): String {
        var str = ""
        var num = valor.toInt()
        var ajusteModulo = 0
        if (num < 0) {
            str += "-"
            num *= -1
            ajusteModulo++
        }
        str += "$"
        var number = Integer.toString(num)
        while (!number.isEmpty()) {
            if (number.length % 3 == 0 && str.length != 1 + ajusteModulo) {
                str += "." + number[0]
            } else {
                str += number[0]
            }
            number = number.substring(1)
        }
        val decimal = "%.2f".formatted(valor % 1).substring(1 + ajusteModulo)
        str += decimal
        return str
    }

    fun toPTJ(porcentaje: Double): String {
        return "%.2f".formatted(porcentaje * 100) + "%"
    }

    fun isDouble(str: String): Boolean {
        return try {
            str.toDouble()
            true
        } catch (numberFormat: NumberFormatException) {
            false
        }
    }

    /*
    fun toArrayList(str: String?): ArrayList<String?> {
        val arrayList: ArrayList<String?> = ArrayList<Any?>()
        val tokens = StringTokenizer(str)
        while (tokens.hasMoreTokens()) {
            arrayList.add(tokens.nextToken())
        }
        return arrayList
    }*/
}