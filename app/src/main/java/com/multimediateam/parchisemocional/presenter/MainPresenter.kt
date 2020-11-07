package com.multimediateam.parchisemocional.presenter

import android.util.Log
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.network.NetworkClient
import com.multimediateam.parchisemocional.data.DatabaseBuilder
import com.multimediateam.parchisemocional.data.DatabaseHelper
import com.multimediateam.parchisemocional.data.DatabaseHelperImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class IMainPresenter @Inject constructor(): MainContract.MainPresenter {

    private val TAG: String = "MainPresenter"

    @Inject lateinit var networkClient: NetworkClient
    @Inject lateinit var emotionDB: DatabaseHelper

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