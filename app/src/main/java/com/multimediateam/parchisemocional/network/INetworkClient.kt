package com.multimediateam.parchisemocional.network

import android.util.Log
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import javax.inject.Inject

class NetworkClient @Inject constructor(): MainContract.NetworkClient{
    private val TAG: String = "NetworkClient"

    override suspend fun sendEmotion(emotion: Emotion) {
        Log.i(TAG, "sending Emotion ${emotion.toString()}")
    }
}