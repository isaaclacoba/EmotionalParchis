package com.multimediateam.parchisemocional.interactor

import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.mockito.*
import org.mockito.BDDMockito.given

class IMainInteractorTest {
    private lateinit var mInteractor: MainContract.MainInteractor

    @Mock lateinit var mRepository: MainContract.MainRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mInteractor = IMainInteractor(mRepository)
    }

    @Test
    fun addEmotion() = runBlocking {

        mInteractor.addEmotion(300f, 500f)
        Mockito.verify(mRepository).saveEmotion(Emotion.createEmotion(300f, 500f))
    }

    @Test
    fun getEmotions() = runBlockingTest {
        Mockito.`when`(mRepository.getEmotions()).thenReturn(listOf(Emotion.createEmotion(300f,500f)))

        val emotions = mInteractor.getEmotions()
        assert(emotions is List<Emotion>)
        Mockito.verify(mRepository).getEmotions()
    }
}