const val SECONDS = 60
const val ONE_HOUR = 60 * SECONDS
const val ONE_DAY = 24 * ONE_HOUR
const val USER = "Пользовтель"
var time = 0


fun main() {
    val userTime = 61
    val userWasOnline = agoToText(userTime)

    println("$USER был(а) в сети ${if (time != 0) "$time $userWasOnline" else userWasOnline}")

}

fun agoToText(userTime: Int): String {
   val text = when {
       userTime <= SECONDS -> "только что"
       userTime in (SECONDS + 1) until ONE_HOUR -> convertInMinutes(userTime)
       userTime in ONE_HOUR until ONE_DAY -> convertInHours(userTime)
       userTime >= ONE_DAY && userTime < 2 * ONE_DAY -> "сегодня"
       userTime >= 2 * ONE_DAY && userTime < 3 * ONE_DAY -> "вчера"
        else -> "давно"
    }
    return text
}

fun convertInMinutes(userTime: Int): String {
    val minutes = userTime / SECONDS
    time += minutes
    val minutesInText = when (minutes) {
        1, 21, 31, 41, 51 -> "минуту назад"
        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> "минуты назад"
        else -> "минут назад"
    }
    return minutesInText
}

fun convertInHours(userTime: Int): String {
    val hours = userTime / ONE_HOUR
    time += hours
    val hoursInText = when (hours) {
        1, 21 -> "час назад"
        2, 3, 4, 22, 23 -> "часа назад"
        else -> "часов назад"
    }
    return hoursInText
}