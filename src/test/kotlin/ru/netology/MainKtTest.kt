package ru.netology

import calculateCommission
import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateCommission_Mastercard_transfers_UpTo75000_RubInMonth() {
        //arrange
        val card = "Mastercard"
        val lastTransfers = 0
        val transfer = 1_000_00

        //act
        val result = calculateCommission(
            typeCard = card,
            sumOfTransfersInMonth = lastTransfers,
            transfer = transfer
        )

        //assert
        assertEquals(0, result)
    }

    @Test
    fun calculateCommission_Maestro_transfers_UpTo75000_RubInMonth() {
        //arrange
        val card = "Maestro"
        val lastTransfers = 0
        val transfer = 1_000_00

        //act
        val result = calculateCommission(
            typeCard = card,
            sumOfTransfersInMonth = lastTransfers,
            transfer = transfer
        )

        //assert
        assertEquals(0, result)
    }

    @Test
    fun calculateCommission_Mastercard_Transfers_Over75000_RubInMonth() {
        //arrange
        val card = "Mastercard"
        val lastTransfers = 75_000_00
        val transfer = 1_000_00

        //act
        val result = calculateCommission(
            typeCard = card,
            sumOfTransfersInMonth = lastTransfers,
            transfer = transfer
        )

        //assert
        assertEquals(26_00, result)
    }

    @Test
    fun calculateCommission_Maestro_Transfers_Over75000_RubInMonth() {
        //arrange
        val card = "Maestro"
        val lastTransfers = 75_000_00
        val transfer = 1_000_00

        //act
        val result = calculateCommission(
            typeCard = card,
            sumOfTransfersInMonth = lastTransfers,
            transfer = transfer
        )

        //assert
        assertEquals(26_00, result)
    }

    @Test
    fun calculate_Minimal_Commission_Visa() {
        //arrange
        val card = "Visa"
        val transfer = 1_000_00

        //act
        val result = calculateCommission(
            typeCard = card,
            sumOfTransfersInMonth = 0,
            transfer = transfer
        )

        //assert
        assertEquals(35_00,result)
    }

    @Test
    fun calculate_Minimal_Commission_Mir() {
        //arrange
        val card = "Мир"
        val transfer = 1_000_00

        //act
        val result = calculateCommission(
            typeCard = card,
            sumOfTransfersInMonth = 0,
            transfer = transfer
        )

        //assert
        assertEquals(35_00,result)
    }

    @Test
    fun calculate_Commission_Visa() {
        //arrange
        val card = "Visa"
        val transfer = 10_000_00

        //act
        val result = calculateCommission(
            typeCard = card,
            sumOfTransfersInMonth = 0,
            transfer = transfer
        )

        //assert
        assertEquals(75_00, result)
    }

    @Test
    fun calculate_Commission_Mir() {
        //arrange
        val card = "Мир"
        val transfer = 10_000_00

        //act
        val result = calculateCommission(
            typeCard = card,
            sumOfTransfersInMonth = 0,
            transfer = transfer
        )

        //assert
        assertEquals(75_00, result)
    }

    @Test
    fun calculateCommission_Vkpay() {
        //arrange
        val card = "VK Pay"
        val transfer = 1_000_00

        //act
        val result = calculateCommission(
            typeCard = card,
            sumOfTransfersInMonth = 0,
            transfer = transfer
        )

        //assert
        assertEquals(0, result)
    }
}