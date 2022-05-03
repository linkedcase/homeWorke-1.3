const val SECONDS = 60
const val ONE_HOUR = 60 * SECONDS
const val ONE_DAY = 24 * ONE_HOUR
const val USER = "Пользовтель"
const val USER_TIME = 26713

fun main() {
    agoToText()
}

fun agoToText() {
    when {
        USER_TIME <= SECONDS -> println("$USER был в сети только что")
        USER_TIME in (SECONDS + 1) until ONE_HOUR -> convertInMinutes()
        USER_TIME in ONE_HOUR until ONE_DAY -> convertInHours()
        USER_TIME >= ONE_DAY && USER_TIME < 2 * ONE_DAY -> println("$USER был в сети сегодня")
        USER_TIME >= 2 * ONE_DAY && USER_TIME < 3 * ONE_DAY -> println("$USER был в сети вчера")
        USER_TIME >= 3 * ONE_DAY -> println("$USER был в сети давно")
    }
}

fun convertInMinutes() {
    val time = USER_TIME / SECONDS
    return when (time) {
        1, 21, 31, 41, 51 -> println("$USER был(а) в сети $time минуту назад")
        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> println("$USER был(а) в сети $time минуты назад")
        else -> println("$USER был(а) в сети $time минут назад")
    }
}

fun convertInHours() {
    val time = USER_TIME / ONE_HOUR
    return when (time) {
        1, 21 -> println("$USER был(а) в сети $time час назад")
        2, 3, 4, 22, 23 -> print("$USER был(а) в сети $time часа назад")
        else -> print("$USER был(а) в сети $time часов назад")
    }
}