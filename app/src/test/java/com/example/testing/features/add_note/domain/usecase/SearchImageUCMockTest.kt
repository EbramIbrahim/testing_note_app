package com.example.testing.features.add_note.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.common.presentation.util.Resource
import com.example.testing.features.add_note.domain.repository.IImageRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

class SearchImageUCMockTest {


    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()


    private val imageRepository = mock<IImageRepository>()
    private lateinit var searchImagesUC: SearchImagesUC


    @Before
    fun setup() {
        searchImagesUC = SearchImagesUC(imageRepository)
    }

    @Test
    fun `search images with empty query, return error`() = runTest {
        searchImagesUC.invoke("")
            .collect { result ->
                when (result) {
                    is Resource.Error -> {
                        assertThat(result.data?.images == null).isTrue()
                    }

                    is Resource.Loading -> Unit
                    is Resource.Success -> Unit
                }
            }
    }

    @Test
    fun `search images with a valid query, return success`() = runTest {
        searchImagesUC.invoke("ok")
            .collect { result ->
                when (result) {
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
                when (result) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        println("imageList Size:${result.data?.images?.size!!}")
                        assertThat(result.data?.images?.size!! > 0).isTrue()
                    }
                }
            }
    }


}


/**
 * To Control the behavior of the dependencies
 * whenever(imageRepository.getImages("query")).thenReturn(Image(emptyList()))
 */




