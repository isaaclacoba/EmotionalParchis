package com.multimediateam.parchisemocional.repository

import android.content.Context
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.model.toDomain
import javax.inject.Inject

class IMainRepository @Inject constructor(): MainContract.MainRepository {
    @Inject lateinit var databaseDataSource: MainContract.DatabaseDataSource

    override suspend fun saveEmotion(emotion: Emotion) {
        databaseDataSource.saveEmotion(emotion.toEntity())
    }

    override suspend fun getEmotions(): List<Emotion> = databaseDataSource.getEmotions().toDomain()
}