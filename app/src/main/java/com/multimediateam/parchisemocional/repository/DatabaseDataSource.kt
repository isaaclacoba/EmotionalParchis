package com.multimediateam.parchisemocional.repository

import android.content.Context
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.EmotionRow
import com.multimediateam.parchisemocional.Network.NetworkClient
import com.multimediateam.parchisemocional.data.DatabaseBuilder
import com.multimediateam.parchisemocional.data.DatabaseHelperImpl

//Todo: Inject Context
class IDatabaseDataSource(applicationContext: Context): MainContract.DatabaseDataSource {
    private val networkClient: NetworkClient = NetworkClient()
    private val emotionDB: DatabaseHelperImpl =
        DatabaseHelperImpl(
            DatabaseBuilder.getInstance(
                applicationContext
            )
        )

    override suspend fun saveEmotion(emotionRow: EmotionRow) {
        emotionDB.insertAll(emotions = *arrayOf(emotionRow))
    }

    override suspend fun getEmotions(): List<EmotionRow> = emotionDB.getEmotions()
}