package com.example.testing.features.add_note.data.model.dto

import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("previewURL")
    val imageUrl: String?,
)