package com.example.marvelapp.network

import com.example.marvelapp.model.characters.CharacterResponse
import com.example.marvelapp.model.comics.ComicsResponse
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
    ): CharacterResponse

    @GET("/v1/public/comics")
    suspend fun getResponseComics(
        @Query("offset") offset: Int?,
//        @Query("orderBy") orderBy: String?,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?,
    ): ComicsResponse

//    @GET("/v1/public/characters")
//    suspend fun getResponseComicsOrderName(
//        @Query("name") name: String,
//        @Query("ts") ts: String?,
//        @Query("hash") hash: String?,
//        @Query("apikey") apikey: String?
//    ): ComicsResponse

}

