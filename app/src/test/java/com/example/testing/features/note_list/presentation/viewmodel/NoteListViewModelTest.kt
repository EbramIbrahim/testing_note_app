package com.example.testing.features.note_list.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.features.note_list.MainCoroutineRule
import com.example.testing.features.note_list.domain.repository.FakeNoteRepository
import com.example.testing.features.note_list.domain.usecase.DeleteNoteUC
import com.example.testing.features.note_list.domain.usecase.GetNoteListUC
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NoteListViewModelTest {

    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var getNoteListUC: GetNoteListUC
    private lateinit var deleteNoteListUC: DeleteNoteUC
    private lateinit var noteListViewModel: NoteListViewModel


    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        getNoteListUC = GetNoteListUC(fakeNoteRepository)
        deleteNoteListUC = DeleteNoteUC(fakeNoteRepository)
        noteListViewModel = NoteListViewModel(getNoteListUC, deleteNoteListUC)

    }


    @Test
    fun `get notes from empty list, return empty note`() {
        fakeNoteRepository.shouldHaveFilledList(false)

        noteListViewModel.loadNote()

        assertThat(noteListViewModel.noteListState.value.isEmpty()).isTrue()
    }

    @Test
    fun `get notes from filled list, return note`() {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.loadNote()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(noteListViewModel.noteListState.value.isNotEmpty()).isTrue()
    }

    @Test
    fun `delete note from list, note is deleted`() {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.loadNote()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        val note = noteListViewModel.noteListState.value[0]

        noteListViewModel.deleteNote(note)
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(noteListViewModel.noteListState.value.contains(note)).isFalse()
    }

    @Test
    fun `order notes by date, note is ordered by date`() {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.loadNote()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        val notes = noteListViewModel.noteListState.value

        for (i in 0 until notes.size - 2) {
            assertThat(notes[i].noteDate).isLessThan(notes[i + 1].noteDate)
        }
    }

    @Test
    fun `order notes by title, note is ordered by title`() {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.changeNoteOrder()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        noteListViewModel.loadNote()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        val notes = noteListViewModel.noteListState.value

        for (i in 0 until notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }

}










