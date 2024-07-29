package com.example.testing.features.add_note.data.repository.remote

import com.example.testing.common.presentation.util.Constant.API_KEY
import com.example.testing.features.add_note.data.model.dto.ImageResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") query: String
    ): ImageResponseDto?

}