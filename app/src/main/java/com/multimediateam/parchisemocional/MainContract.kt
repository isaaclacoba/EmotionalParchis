package com.multimediateam.parchisemocional

import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.model.EmotionRow
import com.multimediateam.parchisemocional.model.Result

interface MainContract {
    interface MainPresenter {
        fun setEmotion(x: Float, y: Float)
        fun sendEmotion()
        fun getEmotionsAsync()
    }

    interface MainInteractor {
        suspend fun addEmotion(emotion: Emotion): Result
        suspend fun addEmotion(x: Float, y: Float): Result
        suspend fun getEmotions(): List<Emotion>
    }

    interface MainRepository {
        suspend fun saveEmotion(emotion: Emotion): Result
        suspend fun getEmotions(): List<Emotion>
    }

    interface DatabaseDataSource {
        suspend fun saveEmotion(emotionRow: EmotionRow): Result
        suspend fun getEmotions(): List<EmotionRow>
    }

    interface NetworkClient {
        suspend fun sendEmotion(emotion: Emotion)
    }
}