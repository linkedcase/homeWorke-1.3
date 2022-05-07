const val COMMISSION_MASTERCARD_MAESTRO = 0.006
const val COMMISSION_VISA_MIR = 0.0075
const val LIMIT_CARD_DAY = 150_000_00
const val LIMIT_CARD_MONTH = 600_000_00
const val LIMIT_VK_TRANSFER = 15_000_00
const val LIMIT_VK_MONTH = 40_000_00
const val MAX_SUM_OF_TRANSFERS = 75_000_00
const val MINIMAL_COMMISSION_VISA_MIR = 35_00

fun main() {
    val typeCard = "Vk Pay"
    val sumOfTransfersInMonth = 0
    val transfer = 6_000_00

    val commission = calculateCommission(typeCard, sumOfTransfersInMonth, transfer)

    printMessage(commission, typeCard, transfer, sumOfTransfersInMonth)
}

fun calculateCommission(
    typeCard: String,
    sumOfTransfersInMonth: Int,
    transfer: Int
): Int {
    val commission = when(typeCard) {
        "Mastercard", "Maestro" -> {
            if ((sumOfTransfersInMonth + transfer) < MAX_SUM_OF_TRANSFERS) {
                0
            }
            else (transfer * COMMISSION_MASTERCARD_MAESTRO).toInt() + 20_00
        }
        "Visa", "Мир" -> {
            val commission = (transfer * COMMISSION_VISA_MIR).toInt()
            if (commission < MINIMAL_COMMISSION_VISA_MIR) {
                MINIMAL_COMMISSION_VISA_MIR
            } else commission
        }
        else -> 0
    }
    return commission
}

fun printMessage(
    commission: Int,
    typeCard: String,
    transfer: Int,
    sumOfTransfersInMonth: Int
) {
    when {
        (typeCard == "Vk Pay" && transfer > LIMIT_VK_TRANSFER) ||
                (typeCard == "Vk Pay" && (transfer + sumOfTransfersInMonth) > LIMIT_VK_TRANSFER)-> {
            val limit = LIMIT_VK_TRANSFER - sumOfTransfersInMonth

            println("По счету $typeCard превышена максимальная сумма перевода за один раз.")
            println("Перевод на сумму: ${transfer / 100} руб. " +
                    "${transfer % 100} коп. отклонен.")
            println("Для перевода за один раз доступно: ${limit / 100} руб. ${limit % 100} коп.\"")
        }

        typeCard == "Vk Pay" -> {
            val limit = LIMIT_VK_MONTH - (sumOfTransfersInMonth + transfer)

            println("Перевод со счета $typeCard на сумму: ${transfer / 100} руб. " +
                    "${transfer % 100} коп.")
            println("Размер комиссии: ${commission / 100} руб. ${commission % 100} коп.")
            println("Доступный месячный лимит по счету $typeCard составляет" +
                    " ${limit / 100} руб. ${limit % 100} коп.")
        }

        transfer > LIMIT_CARD_DAY || (sumOfTransfersInMonth + transfer) >
                LIMIT_CARD_DAY -> {
            val limitDay = LIMIT_CARD_DAY - sumOfTransfersInMonth

            println("Суточный лимит по карте $typeCard превышен.")
            println("Перевод на сумму: ${transfer / 100} руб. " +
                    "${transfer % 100} коп. отклонен.")
            println("Доступный суточный лимит: ${limitDay / 100} руб. ${limitDay % 100} коп.")
        }

        else -> {
            println("Перевод с карты $typeCard на сумму: ${transfer / 100} руб. " +
                    "${transfer % 100} коп.")
            println("Размер комиссии: ${commission / 100} руб. ${commission % 100} коп.")

            val limitDay = LIMIT_CARD_DAY - (sumOfTransfersInMonth + transfer)
            val limitMonth = LIMIT_CARD_MONTH - (sumOfTransfersInMonth + transfer)

            println("По карте $typeCard доступны лимиты " +
                    "Суточный: ${limitDay / 100} руб. ${limitDay % 100} коп. " +
                    "Месячный: ${limitMonth / 100} руб. ${limitMonth % 100} коп.")
        }
    }
}