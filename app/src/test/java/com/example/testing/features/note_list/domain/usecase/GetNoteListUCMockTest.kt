package com.example.testing.features.note_list.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.features.notes.domain.models.Note
import com.example.testing.features.notes.domain.repository.INoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetNoteListUCMockTest {

    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()

    private val noteRepository = mock<INoteRepository>()
    private lateinit var getNoteListUC: GetNoteListUC

    @Before
    fun setup() {
        getNoteListUC = GetNoteListUC(noteRepository)
    }


    @Test
    fun `get all notes list from empty list, return empty`() = runTest {
        // Arrange
        whenever(noteRepository.getNoteList()).thenReturn(emptyList())
        // Act
        val noteList = getNoteListUC.invoke(true)
        // Assert
        assertThat(noteList.isEmpty()).isTrue()
    }

    @Test
    fun `get all notes ordered by title, return notes ordered by title`() = runTest {
        // Arrange
        val expectedNotes =
            mutableListOf(
                Note(1, "d title 1", "desc 1", "image1", 12),
                Note(2, "c title 1", "desc 2", "image2", 9),
                Note(3, "b title 1", "desc 3", "image3", 3),
                Note(4, "a title 1", "desc 4", "image4", 30)
            )
        whenever(noteRepository.getNoteList()).thenReturn(expectedNotes)

        // Act
        val noteList = getNoteListUC.invoke(true)

        // Assert
        assertThat(noteList.isNotEmpty()).isTrue()
        assertThat(noteList[0].title).isLessThan(noteList[1].title)
    }

    @Test
    fun `get all notes ordered by date, return notes ordered by date`() = runTest {
        // Arrange
        val expectedNotes =
            mutableListOf(
                Note(1, "d title 1", "desc 1", "image1", 12),
                Note(2, "c title 1", "desc 2", "image2", 9),
                Note(3, "b title 1", "desc 3", "image3", 3),
                Note(4, "a title 1", "desc 4", "image4", 30)
            )
        whenever(noteRepository.getNoteList()).thenReturn(expectedNotes)

        // Act
        val noteList = getNoteListUC.invoke(false)

        // Assert
        assertThat(noteList.isNotEmpty()).isTrue()
        assertThat(noteList[0].noteDate).isLessThan(noteList[1].noteDate)
    }



}










