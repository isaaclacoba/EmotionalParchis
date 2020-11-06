package com.multimediateam.parchisemocional.Network

import android.util.Log
import com.multimediateam.parchisemocional.model.Emotion

class NetworkClient {
    private val TAG: String = "NetworkClient"
    init {

    }

    public fun sendEmotion(emotion: Emotion) {
        Log.i(TAG, "sending Emotion ${emotion.toString()}")
    }
}