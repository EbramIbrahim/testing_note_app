package com.example.testing.features.note_list.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.common.data.repository.FakeNoteRepository
import com.example.testing.features.notes.domain.models.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeleteNoteListUCTest {

    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var deleteNoteUc: DeleteNoteUC


    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        deleteNoteUc = DeleteNoteUC(fakeNoteRepository)
        fakeNoteRepository.shouldHaveFilledList(true)
    }


    @Test
    fun `delete note from list, note is deleted`() = runTest {

        val note = Note(
            1,
            "d title 1",
            "desc 1",
            "image1",
            12
        )

        val allNotes = fakeNoteRepository.getNoteList()

        deleteNoteUc(note)

        assertThat(allNotes.contains(note)).isFalse()

    }


}
