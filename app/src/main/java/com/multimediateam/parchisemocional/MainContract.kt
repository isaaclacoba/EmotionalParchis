package com.multimediateam.parchisemocional

import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.model.EmotionRow

interface MainContract {
    interface MainPresenter {
        fun setEmotion(x: Float, y: Float)
        fun sendEmotion()
    }

    interface MainInteractor {
        suspend fun addEmotion(x: Float, y: Float)
        suspend fun getEmotions(): List<Emotion>
    }

    interface MainRepository {
        suspend fun saveEmotion(emotion: Emotion)
        suspend fun getEmotions(): List<Emotion>
    }

    interface DatabaseDataSource {
        suspend fun saveEmotion(emotionRow: EmotionRow)
        suspend fun getEmotions(): List<EmotionRow>
    }
}