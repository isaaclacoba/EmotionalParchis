package com.multimediateam.parchisemocional.presenter

import android.content.Context
import android.util.Log
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.Network.NetworkClient
import com.multimediateam.parchisemocional.data.DatabaseBuilder
import com.multimediateam.parchisemocional.data.DatabaseHelperImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface MainPresenter {
    fun setEmotion(x: Float, y: Float)
    fun sendEmotion()
}

class IMainPresenter(applicationContext: Context): MainPresenter {
    private val TAG: String = "MainPresenter"

    private val networkClient: NetworkClient = NetworkClient()
    private val emotionDB: DatabaseHelperImpl =
        DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))

    var emotion: Emotion = Emotion.createEmotion( 0.toFloat(),0.toFloat())

    override fun setEmotion(x: Float, y: Float) {
        emotion.update(x, y)
        Log.i(TAG, "new emotion value: ${emotion.toString()}")
    }

    override fun sendEmotion() {
        GlobalScope.launch {
            emotionDB.insertAll(emotions = *arrayOf(emotion.toEntity()))
            networkClient.sendEmotion(emotion)
        }
    }
}