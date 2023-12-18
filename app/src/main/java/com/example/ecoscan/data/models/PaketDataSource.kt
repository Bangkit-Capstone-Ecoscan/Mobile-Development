package com.example.ecoscan.data.models

object PaketDataSource {
    val paket = listOf(
        Paket(
            id = 1,
            paket = "Bronze",
            price = "15.000",
            desc = "15 Scan / Bulan"
        ),
        Paket(
            id = 2,
            paket = "Silver",
            price = "25.000",
            desc = "30 Scan / Bulan"
        ),
        Paket(
            id = 3,
            paket = "Gold",
            price = "40.000",
            desc = "45 Scan / Bulan"
        )
    )
}