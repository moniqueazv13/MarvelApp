package com.example.marvelapp.repository

import com.example.marvelapp.MD5.Companion.md5
import com.example.marvelapp.model.CharacterResponse
import com.example.marvelapp.network.Endpoint
import com.example.marvelapp.network.RetrofitInit

class RepositoryApi {

    val PUBLIC_KEY: String = "a066570e62f3e61ae507bffc005c1dbd"
    val PRIVATE_KEY: String = "312bbf04fc635e7db0bd7edad4e3a1e3ec19577e"
    val page: Int = 1
    val orderBy: String = "name"
    private val url = "https://gateway.marvel.com"

//    var ts: String? = java.lang.Long.toString(System.currentTimeMillis() / 1000)
//    var hash: String? = md5(ts + PRIVATE_KEY + PUBLIC_KEY)
    val hash = "4169b8590703d178d78a0e79b848336c"

    private var service = Endpoint::class

    private var serviceChar = RetrofitInit(url).create(service)

    suspend fun getCharacterService(): CharacterResponse = serviceChar.getResponseCharacter(
        page, orderBy, "1", PUBLIC_KEY, hash
    )

}