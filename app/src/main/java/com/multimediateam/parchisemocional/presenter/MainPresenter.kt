package com.multimediateam.parchisemocional.presenter

import android.util.Log
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.network.INetworkClient
import com.multimediateam.parchisemocional.data.DatabaseHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class IMainPresenter @Inject constructor(): MainContract.MainPresenter {

    private val TAG: String = "MainPresenter"

    @Inject lateinit var INetworkClient: INetworkClient
    @Inject lateinit var emotionDB: DatabaseHelper

    var emotion: Emotion = Emotion.createEmotion( 0.toFloat(),0.toFloat())

    override fun setEmotion(x: Float, y: Float) {
        emotion.update(x, y)
        Log.i(TAG, "new emotion value: ${emotion.toString()}")
    }

    override fun sendEmotion() {
        GlobalScope.launch {
            emotionDB.insertAll(emotions = *arrayOf(emotion.toEntity()))
            INetworkClient.sendEmotion(emotion)
        }
    }
}