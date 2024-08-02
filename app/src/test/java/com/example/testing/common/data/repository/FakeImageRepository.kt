package com.example.testing.common.data.repository

import com.example.testing.features.add_note.domain.model.Image
import com.example.testing.features.add_note.domain.repository.IImageRepository

class FakeImageRepository : IImageRepository {

    private var shouldReturnError = false
    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getImages(query: String): Image? {
        return if (shouldReturnError)
            null
        else
            Image(listOf("image1", "image2", "image3"))
    }
}