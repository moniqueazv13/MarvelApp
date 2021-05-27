package com.example.marvelapp.model.ui

import com.example.marvelapp.model.comics.Result
import java.io.Serializable

class ComicsUi() : Serializable {
    var id: Int = 0
    var description: String? = ""
    var title: String = ""
    var image: String? = ""
    var price: Double = 0.0
    var favorite: Boolean = false

    constructor(
        id: Int, description: String?,
        title: String,
        image: String?,
        price: Double
    ) : this() {
        this.id = id
        this.description = description
        this.title = title
        this.image = image
        this.price = price
    }
}

fun Result.toComics(): ComicsUi {
    val img = if (thumbnail != null) {
        "${thumbnail.path}.${thumbnail.extension}"
    } else {
        null
    }
    return ComicsUi(
        id,
        description, title, img, prices[0].price
    )
}