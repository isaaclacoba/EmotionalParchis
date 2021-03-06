package com.multimediateam.parchisemocional.repository

import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.EmotionRow
import com.multimediateam.parchisemocional.data.DatabaseHelper
import com.multimediateam.parchisemocional.model.Result

import javax.inject.Inject

class IDatabaseDataSource @Inject constructor(
    var emotionDB: DatabaseHelper
): MainContract.DatabaseDataSource {

    override suspend fun saveEmotion(emotionRow: EmotionRow): Result {
        emotionDB.insertAll(emotions = *arrayOf(emotionRow))
        return Result(true, "Emotion added")
    }

    override suspend fun getEmotions(): List<EmotionRow> = emotionDB.getEmotions()
}