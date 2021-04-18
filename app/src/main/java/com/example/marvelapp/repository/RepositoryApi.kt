package com.example.marvelapp.repository

import com.example.marvelapp.MD5.Companion.md5
import com.example.marvelapp.model.characters.CharacterResponse
import com.example.marvelapp.model.comics.ComicsResponse
import com.example.marvelapp.network.Endpoint
import com.example.marvelapp.network.RetrofitInit

class RepositoryApi {

    val PUBLIC_KEY: String = "6ad9f137b01ef70b02428469c8d3978b"
    val PRIVATE_KEY = "00ec465d7b87a8fe8e3b7d99810febc82a65a6b8"
    val page: Int = 10
    val orderBy: String = "name"
    private val url = "https://gateway.marvel.com"
    val ts = java.lang.Long.toString(System.currentTimeMillis() / 1000)
    val hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY)

    private var service = Endpoint::class

    private var serviceChar = RetrofitInit(url).create(service)

    suspend fun getCharacterService(): CharacterResponse = serviceChar.getResponseCharacter(
        page, orderBy, ts, hash, PUBLIC_KEY
    )

    suspend fun getCharacterOrderName(name: String): CharacterResponse =
        serviceChar.getResponseCharacterOrderName(
            name, ts, hash, PUBLIC_KEY
        )

    // copiei e colei a getCharacterService, alterando nome e método do Endpoint
    suspend fun getComicsService(): ComicsResponse = serviceChar.getResponseComics(
        page, ts, PUBLIC_KEY, hash
    )

    //     copiei e colei a getCharacterService, alterando nome e método do Endpoint
//    suspend fun getComicsOrderName(name: String): ComicsResponse =
//        serviceChar.getResponseComicsOrderName(
//            name, ts, hash, PUBLIC_KEY
//        )
}