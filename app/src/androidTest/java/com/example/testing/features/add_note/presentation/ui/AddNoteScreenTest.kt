package com.example.testing.features.add_note.presentation.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.testing.common.di.NoteAppModule
import com.example.testing.common.presentation.ui.MainActivity
import com.example.testing.common.presentation.util.Constant
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(NoteAppModule::class)
class AddNoteScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun addNoteScreenEndToEnd() {
        composeRule.onNodeWithTag(Constant.ADD_NOTE_FAB)
            .performClick()

        composeRule.onNodeWithTag(Constant.TITLE_TEXT_FIELD)
            .performTextInput("title")

        composeRule.onNodeWithTag(Constant.DESCRIPTION_TEXT_FIELD)
            .performTextInput("description")

        composeRule.onNodeWithTag(Constant.NOTE_IMAGE + "")
            .performClick()

        composeRule.onNodeWithTag(Constant.SEARCH_IMAGE_DIALOG)
            .assertIsDisplayed()

        composeRule.onNodeWithTag(Constant.SEARCH_IMAGE_TEXT_FIELD)
            .performTextInput("query")

        runBlocking {
            delay(1000)
        }
        composeRule.onNodeWithTag(Constant.PICKED_IMAGE + "image1")
            .performClick()

        composeRule.onNodeWithTag(Constant.NOTE_IMAGE + "image1")
            .assertIsDisplayed()
    }
}





















