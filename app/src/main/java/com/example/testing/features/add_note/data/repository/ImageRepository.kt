package com.example.testing.features.add_note.data.repository

import com.example.testing.features.add_note.data.mapper.toImage
import com.example.testing.features.add_note.data.repository.remote.ImageApi
import com.example.testing.features.add_note.domain.model.Image
import com.example.testing.features.add_note.domain.repository.IImageRepository
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val api: ImageApi
): IImageRepository {

    override suspend fun getImages(query: String): Image? {
        val imageDto = api.getImages(query = query)
        return imageDto?.toImage()
    }
}







