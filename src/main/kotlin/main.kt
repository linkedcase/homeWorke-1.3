const val COMMISSION_MASTERCARD_MAESTRO: Double = 0.006
const val COMMISSION_VISA_MIR: Double = 0.0075
const val LIMIT_CARD_DAY = 150_000_00
const val LIMIT_CARD_MONTH = 600_000_00
const val MAX_SUM_OF_TRANSFERS = 75_000_00
const val LIMIT_VK_TRANSFER = 15_000_00
const val LIMIT_VK_MONTH = 40_000_00
const val MINIMAL_COMMISSION_VISA_MIR = 35_00

fun main() {
    val typeCard = "VK Pay"
    val sumOfTransfersInMonth = 0
    val transfer = 5_000_00

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
        (typeCard == "VK Pay" && (transfer + sumOfTransfersInMonth) > LIMIT_VK_TRANSFER) -> {
            println("Лимит по $typeCard превышен")
            println("Максимальная сумма перевода со счёта $typeCard -" +
                    "${LIMIT_VK_TRANSFER / 100} рублей за один раз  и ${LIMIT_VK_MONTH / 100} рублей в месяц")
        }
        (transfer + sumOfTransfersInMonth) > LIMIT_CARD_DAY -> {
            println("Лимит по карте $typeCard превышен")
            println("Максимальная сумма переводов по одной карте - ${LIMIT_CARD_DAY / 100} рублей в сутки" +
                    "и ${LIMIT_CARD_MONTH / 100} рублей в месяц")
        }
        else -> {
            println("Тип карты/счета: $typeCard")
            println("Сумма перевода: ${transfer / 100} руб.")
            println("Размер комиссии: ${commission / 100} руб. ${commission % 100} коп.")
        }
    }
}