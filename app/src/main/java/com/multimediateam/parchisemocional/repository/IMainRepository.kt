package com.multimediateam.parchisemocional.repository

import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.model.toDomain
import com.multimediateam.parchisemocional.model.Result
import javax.inject.Inject

class IMainRepository @Inject constructor(
    private var networkClient: MainContract.NetworkClient,
    private var databaseDataSource: MainContract.DatabaseDataSource
): MainContract.MainRepository {

    override suspend fun saveEmotion(emotion: Emotion): Result {
        networkClient.sendEmotion(emotion)
        return databaseDataSource.saveEmotion(emotion.toEntity())
    }

    override suspend fun getEmotions(): List<Emotion> = databaseDataSource.getEmotions().toDomain()
}