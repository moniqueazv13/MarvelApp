package com.example.marvelapp.network

import com.example.marvelapp.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("/v1/public/characters")
    suspend fun getResponseCharacter(
        @Query("offset") offset: Int?,
        @Query("orderBy") orderBy: String?,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
        @Query("apikey") apikey: String?
    ): CharacterResponse

    @GET("/v1/public/characters")
    suspend fun getResponseCharacterOrderName(
        @Query("name") name: String,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
        @Query("apikey") apikey: String?
    ):CharacterResponse
}

