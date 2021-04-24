package com.example.marvelapp.model.ui

import com.example.marvelapp.model.characters.Thumbnail
import com.example.marvelapp.model.comics.Price
import com.example.marvelapp.model.comics.Result
import java.io.Serializable

data class ComicsUi(
    val description: String?,
    val title: String,
    val image: Thumbnail?,
    val price: Price
): Serializable

fun Result.toComics(): ComicsUi {
    return ComicsUi(
        description, title, thumbnail, prices[0]
    )
}