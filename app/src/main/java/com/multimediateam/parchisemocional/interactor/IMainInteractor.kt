package com.multimediateam.parchisemocional.interactor

import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.model.Result

import javax.inject.Inject

class IMainInteractor @Inject constructor(var repository: MainContract.MainRepository): MainContract.MainInteractor {

    override suspend fun addEmotion(x: Float, y: Float): Result {
        val emotion = Emotion.createEmotion( x, y)
        return addEmotion(emotion)
    }

    override suspend fun addEmotion(emotion: Emotion): Result {
        return repository.saveEmotion(emotion)
    }

    override suspend fun getEmotions(): List<Emotion> = repository.getEmotions()
}