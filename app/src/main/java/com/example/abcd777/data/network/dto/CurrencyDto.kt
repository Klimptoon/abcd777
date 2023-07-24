package com.example.abcd777.data.network.dto

import com.example.abcd777.domain.models.Currency

data class ForApiItem(
    val EUR_in: String,
    val EUR_out: String,
    val RUB_in: String,
    val RUB_out: String,
    val USD_in: String,
    val USD_out: String,
)

fun ForApiItem.toCurrency(): Currency {
    return Currency(
        EUR_in = EUR_in,
        EUR_out = EUR_out,
        RUB_in = RUB_in,
        RUB_out = RUB_out,
        USD_in = USD_in,
        USD_out = USD_out
    )
}
