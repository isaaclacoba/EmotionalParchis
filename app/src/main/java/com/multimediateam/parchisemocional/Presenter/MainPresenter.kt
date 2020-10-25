package com.multimediateam.parchisemocional.Presenter

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.multimediateam.parchisemocional.Model.Emotion
import com.multimediateam.parchisemocional.Network.NetworkClient
import com.multimediateam.parchisemocional.data.AppDatabase
import com.multimediateam.parchisemocional.data.DatabaseBuilder
import com.multimediateam.parchisemocional.data.DatabaseHelperImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(applicationContext: Context) {
    private val TAG: String = "MainPresenter"

    private val networkClient: NetworkClient = NetworkClient()
    private val emotionDB: DatabaseHelperImpl =
        DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))

    var emotion: Emotion = Emotion.createEmotion( 0.toFloat(),0.toFloat())

    public fun setEmotion(x: Float, y: Float) {
        emotion.update(x, y)
        Log.i(TAG, "new emotion value: ${emotion.toString()}")
    }

    public fun sendEmotion() {
        GlobalScope.launch {
            emotionDB.insertAll(emotions = *arrayOf(emotion.toEntity()))
            networkClient.sendEmotion(emotion)
        }
    }
}