package com.example.testing.features.notes.data.models.entity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.testing.features.notes.data.repository.entity.NoteDao
import com.example.testing.features.notes.data.repository.entity.NoteDatabase
import com.example.testing.features.notes.data.repository.entity.NoteEntity
import com.example.testing.features.notes.di.DatabaseModule
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
@SmallTest
class TestNoteDao {


    @get:Rule
    val hiltRule = HiltAndroidRule(this)


    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()


    @Inject
    lateinit var noteDatabase: NoteDatabase

    private lateinit var noteDao: NoteDao

    @Before
    fun setUp() {
        hiltRule.inject()
        noteDao = noteDatabase.noteDao
    }

    @After
    fun tearsDown() {
        noteDatabase.close()
    }

    @Test
    fun getAllNotesFromEmptyDb_noteListIsEmpty() = runTest {
        assertThat(noteDao.getNoteEntities().isEmpty())
    }

    @Test
    fun getAllNotesFromDb_noteListIsNotEmpty() = runTest {
        for(i in 0..4) {
            val note = NoteEntity(
                id = i,
                title = "title $i",
                description = "description $i",
                imageUrl = "image $i",
                noteDate = System.currentTimeMillis()
            )
            noteDao.upsertNoteEntity(note)
        }
        assertThat(noteDao.getNoteEntities().isNotEmpty())
    }

    @Test
    fun getNoteFromDb_noteIsUpserted() = runTest {
            val note = NoteEntity(
                id = 1,
                title = "title",
                description = "description",
                imageUrl = "image",
                noteDate = System.currentTimeMillis()
            )
            noteDao.upsertNoteEntity(note)

        assertThat(noteDao.getNoteEntities().contains(note))
    }

    @Test
    fun deleteNoteFromDb_noteIsDeleted() = runTest {
        val note = NoteEntity(
            id = 1,
            title = "title",
            description = "description",
            imageUrl = "image",
            noteDate = System.currentTimeMillis()
        )
        noteDao.deleteNoteEntity(note)

        assertThat(noteDao.getNoteEntities().contains(note)).isFalse()
    }

}













