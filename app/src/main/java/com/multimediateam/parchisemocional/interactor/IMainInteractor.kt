package com.multimediateam.parchisemocional.interactor

import android.content.Context
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.repository.IMainRepository
import javax.inject.Inject

class IMainInteractor(var repository: MainContract.MainRepository): MainContract.MainInteractor {

    override suspend fun addEmotion(x: Float, y: Float) {
        val emotion = Emotion.createEmotion( x, y)
        repository.saveEmotion(emotion)
    }

    override suspend fun getEmotions(): List<Emotion> = repository.getEmotions()
}