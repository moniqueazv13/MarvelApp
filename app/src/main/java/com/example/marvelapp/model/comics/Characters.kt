package com.example.marvelapp.model.comics

import java.io.Serializable

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
): Serializable