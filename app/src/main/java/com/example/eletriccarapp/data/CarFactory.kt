package com.example.eletriccarapp.data

import com.example.eletriccarapp.domain.Car

object CarFactory {

    val list = listOf(
        Car(
            id = 1,
            price = "R$ 300.000,00",
            battery = "300 Kwh",
            power = "200 cv",
            recharge = "30 min",
            urlPhoto = "www.google.com.br"
        ),
        Car(
            id = 2,
            price = "R$ 75.000,00",
            battery = "120 Kwh",
            power = "80 cv",
            recharge = "45 min",
            urlPhoto = "www.google.com.br"
        ),
        Car(
            id = 3,
            price = "R$ 126.000,00",
            battery = "110 Kwh",
            power = "100 cv",
            recharge = "30 min",
            urlPhoto = "www.google.com.br"
        )
    )
}