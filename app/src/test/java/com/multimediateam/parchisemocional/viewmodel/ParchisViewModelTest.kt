package com.multimediateam.parchisemocional.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.model.Result
import com.multimediateam.parchisemocional.rules.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

//WIP
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ParchisViewModelTest {
    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var mInteractor: MainContract.MainInteractor

    private lateinit var parchisViewModel: ParchisViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        parchisViewModel = ParchisViewModel(mInteractor,
            coroutineRule.testCoroutineDispatcher,
            SavedStateHandle())
        parchisViewModel.coroutineScope = coroutineRule.testCoroutineScope

    }
    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T

    @Test
    fun sendEmotion() {
        coroutineRule.runBlockingTest {
            Mockito.`when`(mInteractor.addEmotion(Mockito.any(Emotion::class.java))).thenReturn(
                Result(true, "Emotion added")
            )

            parchisViewModel.setEmotion(300f, 500f)

            parchisViewModel.sendEmotion()

            println("result: ${parchisViewModel.mResult.value}")
            //Due to coroutines continuation object, this fails although is exactly the same obj
            Mockito.verify(mInteractor).addEmotion(parchisViewModel.mEmotion)
            Assert.assertTrue(
                "mResult is not properly updated",
                parchisViewModel.mResult.value == "Emotion added"
            )
        }
    }


    @Test
    fun getEmotionsAsync() {
    }
}