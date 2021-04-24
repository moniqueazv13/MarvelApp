package com.example.marvelapp.model.comics

import java.io.Serializable

data class Price(
    val price: Double,
    val type: String
): Serializable