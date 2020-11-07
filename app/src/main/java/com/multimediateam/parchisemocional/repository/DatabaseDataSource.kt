package com.multimediateam.parchisemocional.repository

import android.content.Context
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.EmotionRow
import com.multimediateam.parchisemocional.network.NetworkClient
import com.multimediateam.parchisemocional.data.DatabaseBuilder
import com.multimediateam.parchisemocional.data.DatabaseHelper
import com.multimediateam.parchisemocional.data.DatabaseHelperImpl
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

//Todo: Inject Context
class IDatabaseDataSource @Inject constructor(): MainContract.DatabaseDataSource {

    @Inject lateinit var networkClient: NetworkClient
    @Inject lateinit var emotionDB: DatabaseHelper

    override suspend fun saveEmotion(emotionRow: EmotionRow) {
        emotionDB.insertAll(emotions = *arrayOf(emotionRow))
    }

    override suspend fun getEmotions(): List<EmotionRow> = emotionDB.getEmotions()
}