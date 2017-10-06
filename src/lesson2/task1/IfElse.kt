@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int) =
        when {
            ((age % 100) in 5..20) -> "$age лет"
            ((age % 10) == 1) -> "$age год"
            ((age % 10) in 2..4) -> "$age года"
            else -> "$age лет"
        }


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val path = v1 * t1 + v2 * t2 + v3 * t3
    return when {
        (path / 2 <= v1 * t1) -> path / (2 * v1)
        (path / 2 <= v1 * t1 + v2 * t2) -> (path / 2 - v1 * t1) / v2 + t1
        else -> (path / 2 - v1 * t1 - v2 * t2) / v3 + t1 + t2
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    if ((kingX == rookX1) || (kingY == rookY1) && (kingX == rookX2 || kingY == rookY2))
        return 3
    else if (kingX == rookX1 || kingY == rookY1)
        return 1
    else if (kingX == rookX2 || kingY == rookY2)
        return 2
    return 0

}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val delX = (kingX - bishopX)
    val delY = (kingY - bishopY)
    if ((Math.abs(delX) == Math.abs(delY)) && (kingX == rookX || kingY == rookY))
        return 3
    if (Math.abs(delX) == Math.abs(delY))
        return 2
    if (kingX == rookX || kingY == rookY)
        return 1
    return 0

}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val sqrA = sqr(a)
    val sqrB = sqr(b)
    val sqrC = sqr(c)

    if ((a > b + c) || (b > a + c) || (c > b + a)) {
        return -1
    }
    if ((sqrC == sqrB + sqrA) || (sqrB == sqrA + sqrC) || (sqrA == sqrB + sqrC)) {
        return 1
    }
    if (((sqrC < sqrB + sqrA) && (c > a) && (c > b)) || ((sqrB < sqrA + sqrC) && (b > a) && (b > c)) || ((sqrA < sqrB + sqrC) && (a > c) && (a > b))) {
        return 2
    }
    return 0
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
        ((a >= c) && (b <= d)) -> b - a
        ((c >= a) && (d <= b)) -> d - c
        ((b >= c) && (b <= d)) -> b - c
        ((d >= a) && (d <= b)) -> d - a
        else -> -1

    }
