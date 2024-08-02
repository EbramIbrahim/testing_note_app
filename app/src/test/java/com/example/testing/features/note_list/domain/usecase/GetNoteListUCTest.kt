package com.example.testing.features.note_list.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.common.data.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetNoteListUCTest {

    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var getNoteListUC: GetNoteListUC


    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        getNoteListUC = GetNoteListUC(fakeNoteRepository)
    }


    @Test
    fun `get notes from empty list, return empty`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(false)
        val notes = getNoteListUC(true)
        println("Empty Notes $notes")

        assertThat(notes.isEmpty()).isTrue()
    }

    @Test
    fun `get note list ordered by title, return notes ordered by title`() = runTest {

        fakeNoteRepository.shouldHaveFilledList(true)
        val notes = getNoteListUC(true)

        println("Title Notes $notes")
        for (i in 0 until notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }

    }

    @Test
    fun `get note list ordered by date, return notes ordered by date`() = runTest {

        fakeNoteRepository.shouldHaveFilledList(true)
        val notes = getNoteListUC(false)

        println("date Notes $notes")
        for (i in 0 until notes.size - 2) {
            assertThat(notes[i].noteDate).isLessThan(notes[i + 1].noteDate)
        }

    }
}






