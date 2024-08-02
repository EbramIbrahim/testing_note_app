package com.example.testing.features.add_note.domain.usecase

import com.example.testing.common.presentation.util.Resource
import com.example.testing.features.add_note.domain.model.Image
import com.example.testing.features.add_note.domain.repository.IImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchImagesUC @Inject constructor(
    private val repository: IImageRepository
) {
    suspend operator fun invoke(query: String): Flow<Resource<Image>> = flow {
        emit(Resource.Loading())

        if (query.isEmpty()){
            emit(Resource.Error())
            emit(Resource.Loading(false))
            return@flow
        }

        val images = try {
            repository.getImages(query)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error())
            emit(Resource.Loading(false))
            return@flow
        }
        images?.let {
            emit(Resource.Success(it))
            emit(Resource.Loading(false))
            return@flow
        }
        emit(Resource.Error())
        emit(Resource.Loading(false))

    }
}






