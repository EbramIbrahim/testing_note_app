package com.example.testing.features.notes.domain.models

data class Note(
    val id: Int = 0,
    val title: String,
    val description: String,
    val imageUrl: String,
    val noteDate: Long
)
