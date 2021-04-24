package com.example.marvelapp.model.characters

import com.example.marvelapp.model.characters.Result

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)