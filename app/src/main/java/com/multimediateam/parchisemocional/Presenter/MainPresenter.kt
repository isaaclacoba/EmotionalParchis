package com.multimediateam.parchisemocional.Presenter

import com.multimediateam.parchisemocional.Model.Emotion
import com.multimediateam.parchisemocional.Network.NetworkClient

class MainPresenter {
    private val networkClient: NetworkClient = NetworkClient()

    var emotion: Emotion = Emotion.createEmotion(0.toFloat(),0.toFloat())

    public fun setEmotion(x: Float, y: Float) {
        emotion = Emotion.createEmotion(x, y)
    }

    public fun sendEmotion() {
        networkClient.sendEmotion(emotion)
    }
}