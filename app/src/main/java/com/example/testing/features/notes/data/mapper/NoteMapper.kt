package com.example.testing.features.notes.data.mapper

import com.example.testing.common.data.mapper.Mapper
import com.example.testing.features.notes.data.repository.entity.NoteEntity
import com.example.testing.features.notes.domain.models.Note


object NoteMapper : Mapper<NoteEntity, Note> {

    override fun entityToDomain(entity: List<NoteEntity>): List<Note> {
       return entity.map {
            Note(
               id = it.id ?: -1,
               title = it.title,
               description = it.description,
               imageUrl = it.imageUrl,
               noteDate = it.noteDate
           )
       }
    }

    override fun domainToEntityForUpsert(domain: Note): NoteEntity {
        return NoteEntity(
            title = domain.title,
            description = domain.description,
            imageUrl = domain.imageUrl,
            noteDate = domain.noteDate,
        )
    }

    override fun domainToEntityForDelete(domain: Note): NoteEntity {
        return NoteEntity(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            imageUrl = domain.imageUrl,
            noteDate = domain.noteDate
        )
    }
}







