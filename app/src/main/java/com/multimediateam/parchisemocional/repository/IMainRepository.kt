package com.multimediateam.parchisemocional.repository

import android.content.Context
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.model.toDomain

class IMainRepository(applicationContext: Context): MainContract.MainRepository {
    private val databaseDataSource: MainContract.DatabaseDataSource by lazy {
        IDatabaseDataSource(
            applicationContext
        )
    }
    override suspend fun saveEmotion(emotion: Emotion) {
        databaseDataSource.saveEmotion(emotion.toEntity())
    }

    override suspend fun getEmotions(): List<Emotion> = databaseDataSource.getEmotions().toDomain()
}