package com.example.testing.features.add_note.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.common.data.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UpsertNoteUCTest {

    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var upsertNoteListUC: UpsertNoteUC


    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        upsertNoteListUC = UpsertNoteUC(fakeNoteRepository)
    }



    @Test
    fun `upsert note with empty title, return false`() = runTest {
        val isInserted = upsertNoteListUC.invoke(
            "",
            "description",
            "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty description, return false`() = runTest {
        val isInserted = upsertNoteListUC.invoke(
            "title",
            "",
            "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty image, return true`() = runTest {
        val isInserted = upsertNoteListUC.invoke(
            "title",
            "description",
            ""
        )

        assertThat(isInserted).isTrue()
    }

    @Test
    fun `upsert a valid note, return true`() = runTest {
        val isInserted = upsertNoteListUC.invoke(
            "title",
            "description",
            "image"
        )

        assertThat(isInserted).isTrue()
    }




}




