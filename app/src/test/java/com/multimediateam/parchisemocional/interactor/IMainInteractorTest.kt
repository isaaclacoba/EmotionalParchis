package com.multimediateam.parchisemocional.interactor

import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.model.Result
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.mockito.*

class IMainInteractorTest {
    private lateinit var mInteractor: MainContract.MainInteractor

    @Mock lateinit var mRepository: MainContract.MainRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mInteractor = IMainInteractor(mRepository)
    }

    @Test
    fun addEmotion() {
        val result = runBlocking {
            Mockito.`when`(mRepository.saveEmotion(Emotion.createEmotion(300f, 500f))).thenReturn(
                Result(true, "Emotion added")
            )

            val result = mInteractor.addEmotion(300f, 500f)
            assert(result.isSucess)

            Mockito.verify(mRepository).saveEmotion(Emotion.createEmotion(300f, 500f))
        }

    }

    @Test
    fun getEmotions() = runBlockingTest {
        Mockito.`when`(mRepository.getEmotions()).thenReturn(listOf(Emotion.createEmotion(300f,500f)))

        val emotions = mInteractor.getEmotions()
        assert(emotions is List<Emotion>)
        Mockito.verify(mRepository).getEmotions()
    }
}