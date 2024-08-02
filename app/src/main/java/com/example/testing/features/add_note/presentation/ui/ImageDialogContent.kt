package com.example.testing.features.add_note.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.testing.R
import com.example.testing.common.presentation.util.Constant
import com.example.testing.features.add_note.presentation.viewmodel.AddNoteState

@Composable
fun ImageDialogContent(
    addNoteState: AddNoteState,
    onSearchQueryChanged: (String) -> Unit,
    onImageClick: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .clip(RoundedCornerShape(26.dp))
            .background(MaterialTheme.colorScheme.background)
            .testTag(Constant.SEARCH_IMAGE_DIALOG),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag(Constant.SEARCH_IMAGE_TEXT_FIELD),
            value = addNoteState.searchQuery,
            onValueChange = {
                onSearchQueryChanged(it)
            },
            label = {
                Text(text = stringResource(id = R.string.search_image))
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (addNoteState.isLoadingImages) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                itemsIndexed(addNoteState.imageList) { index, url ->
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .clickable { onImageClick(url) }
                            .testTag(Constant.PICKED_IMAGE + url),
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(url)
                            .size(Size.ORIGINAL)
                            .build(),
                        contentDescription = stringResource(R.string.image),
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }

    }

}







