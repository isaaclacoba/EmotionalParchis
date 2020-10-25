package com.multimediateam.parchisemocional.data

import com.multimediateam.parchisemocional.Model.EmotionRow

interface DatabaseHelper {

    suspend fun getEmotions(): List<EmotionRow>

    suspend fun insertAll(vararg emotions: EmotionRow)

    suspend fun deleteEmotion(emotion: EmotionRow)
}

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getEmotions(): List<EmotionRow> = appDatabase.emotionDao().getAll()

    override suspend fun insertAll(vararg emotions: EmotionRow) = appDatabase.emotionDao().insertAll(
        *emotions)

    override suspend fun deleteEmotion(emotion: EmotionRow) = appDatabase.emotionDao().delete(emotion)
}