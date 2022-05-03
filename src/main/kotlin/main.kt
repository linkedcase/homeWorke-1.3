const val MAX_SUM_OF_TRANSFERS = 75_000_00
const val LIMIT_CARD_DAY = 150_000_00
const val LIMIT_CARD_MONTH = 600_000_00
const val LIMIT_VK_TRANSFER = 15_000_00
const val LIMIT_VK_MONTH = 40_000_00
const val MINIMAL_COMMISSION_VISA_MIR = 35_00

const val TYPE_CARD = "vkpay"
const val AMOUNT_FOR_TRANSFER = 6_000_00
var sumOfTransfersInMonth = 0

fun main() {
    val commission = calculateCommission(TYPE_CARD, AMOUNT_FOR_TRANSFER, sumOfTransfersInMonth)

    printMessage(commission, TYPE_CARD, AMOUNT_FOR_TRANSFER)
}

fun calculateCommission(
    TYPE_CARD: String,
    AMOUNT_FOR_TRANSFER: Int,
    SUM_OF_TRANSFERS_IN_MONTH: Int,
    commissionMastercardMaestro: Double = 0.006,
    COMMISSION_VISA_MIR: Double = 0.0075
): Int {
    val commission = when(TYPE_CARD) {
        "mastercard", "maestro" -> {
            if ((SUM_OF_TRANSFERS_IN_MONTH + AMOUNT_FOR_TRANSFER) < MAX_SUM_OF_TRANSFERS) {
                0
            }
            else (AMOUNT_FOR_TRANSFER * commissionMastercardMaestro).toInt() + 20_00
        }
        "visa", "mir" -> {
            val commission = (AMOUNT_FOR_TRANSFER * COMMISSION_VISA_MIR).toInt()
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
    TYPE_CARD: String,
    AMOUNT_FOR_TRANSFER: Int
) {
    when {
        (TYPE_CARD == "vkpay" && AMOUNT_FOR_TRANSFER > LIMIT_VK_TRANSFER) ||
                (TYPE_CARD == "vkpay" && (AMOUNT_FOR_TRANSFER + sumOfTransfersInMonth) > LIMIT_VK_TRANSFER)-> {
            val limit = LIMIT_VK_TRANSFER - sumOfTransfersInMonth

            println("По счету $TYPE_CARD превышена максимальная сумма перевода за один раз.")
            println("Перевод на сумму: ${AMOUNT_FOR_TRANSFER / 100} руб. " +
                    "${AMOUNT_FOR_TRANSFER % 100} коп. отклонен.")
            println("Для перевода за один раз доступно: ${limit / 100} руб. ${limit % 100} коп.\"")
        }

        TYPE_CARD == "vkpay" -> {
            val limit = LIMIT_VK_MONTH - (sumOfTransfersInMonth + AMOUNT_FOR_TRANSFER)

            println("Перевод со счета $TYPE_CARD на сумму: ${AMOUNT_FOR_TRANSFER / 100} руб. " +
                    "${AMOUNT_FOR_TRANSFER % 100} коп.")
            println("Размер комиссии: ${commission / 100} руб. ${commission % 100} коп.")
            println("Доступный месячный лимит по счету $TYPE_CARD составляет" +
                    " ${limit / 100} руб. ${limit % 100} коп.")
        }

        AMOUNT_FOR_TRANSFER > LIMIT_CARD_DAY || (sumOfTransfersInMonth + AMOUNT_FOR_TRANSFER) >
                LIMIT_CARD_DAY -> {
            val limitDay = LIMIT_CARD_DAY - sumOfTransfersInMonth

            println("Суточный лимит по карте $TYPE_CARD превышен.")
            println("Перевод на сумму: ${AMOUNT_FOR_TRANSFER / 100} руб. " +
                    "${AMOUNT_FOR_TRANSFER % 100} коп. отклонен.")
            println("Доступный суточный лимит: ${limitDay / 100} руб. ${limitDay % 100} коп.")
        }

        else -> {
            println("Перевод с карты $TYPE_CARD на сумму: ${AMOUNT_FOR_TRANSFER / 100} руб. " +
                    "${AMOUNT_FOR_TRANSFER % 100} коп.")
            println("Размер комиссии: ${commission / 100} руб. ${commission % 100} коп.")

            val limitDay = LIMIT_CARD_DAY - (sumOfTransfersInMonth + AMOUNT_FOR_TRANSFER)
            val limitMonth = LIMIT_CARD_MONTH - (sumOfTransfersInMonth + AMOUNT_FOR_TRANSFER)

            println("По карте $TYPE_CARD доступны лимиты " +
                    "Cуточный: ${limitDay / 100} руб. ${limitDay % 100} коп. " +
                    "Месячный: ${limitMonth / 100} руб. ${limitMonth % 100} коп.")
        }
    }
}