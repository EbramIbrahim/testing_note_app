package com.example.testing.features.add_note.domain.repository

import com.example.testing.features.add_note.domain.model.Image

interface IImageRepository {

    suspend fun getImages(query: String): Image?
}