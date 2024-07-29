package com.example.testing.features.add_note.data.mapper

import com.example.testing.features.add_note.data.model.dto.ImageResponseDto
import com.example.testing.features.add_note.domain.model.Image



fun ImageResponseDto.toImage(): Image {
    return Image(
        images = this.hits?.map { imageDto ->
            imageDto.imageUrl.orEmpty()
        } ?: emptyList()
    )
}