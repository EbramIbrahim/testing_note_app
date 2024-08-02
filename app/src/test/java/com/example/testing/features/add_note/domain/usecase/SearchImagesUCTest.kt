package com.example.testing.features.add_note.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.common.data.repository.FakeImageRepository
import com.example.testing.common.data.repository.FakeNoteRepository
import com.example.testing.common.presentation.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SearchImagesUCTest {

    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()

    private lateinit var fakeImageRepository: FakeImageRepository
    private lateinit var searchImagesUC: SearchImagesUC

    @Before
    fun setUp() {
        fakeImageRepository = FakeImageRepository()
        searchImagesUC = SearchImagesUC(fakeImageRepository)
    }


    @Test
    fun `search images with empty query, return error`() = runTest {
        searchImagesUC.invoke("")
            .collect { result ->
                when(result) {
                    is Resource.Error -> {
                        assertThat(result.data?.images == null).isTrue()
                    }
                    is Resource.Loading -> Unit
                    is Resource.Success -> Unit
                }
            }
    }

    @Test
    fun `search images with valid query, return network error`() = runTest {
        fakeImageRepository.setShouldReturnError(true)
        searchImagesUC.invoke("")
            .collect { result ->
                when(result) {
                    is Resource.Error -> {
                        assertThat(result.data?.images == null).isTrue()
                    }
                    is Resource.Loading -> Unit
                    is Resource.Success -> Unit
                }
            }
    }

    @Test
    fun `search images with valid query, return success`() = runTest {
        searchImagesUC.invoke("query")
            .collect { result ->
                when(result) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        assertThat(result.data?.images != null).isTrue()
                    }
                }
            }
    }


    @Test
    fun `search images with valid query, return image list`() = runTest {
        searchImagesUC.invoke("query")
            .collect { result ->
                when(result) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        assertThat(result.data?.images?.size!! > 0).isTrue()
                    }
                }
            }
    }



}









