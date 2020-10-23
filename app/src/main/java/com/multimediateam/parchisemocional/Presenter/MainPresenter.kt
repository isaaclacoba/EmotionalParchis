package com.multimediateam.parchisemocional.Presenter

import com.multimediateam.parchisemocional.Model.Emotion
import com.multimediateam.parchisemocional.Network.NetworkClient

class MainPresenter {
    private val networkClient: NetworkClient = NetworkClient()


    public fun sendEmotion(x: Float, y: Float) {

        networkClient.sendEmotion(Emotion.createEmotion(x, y))
    }
}