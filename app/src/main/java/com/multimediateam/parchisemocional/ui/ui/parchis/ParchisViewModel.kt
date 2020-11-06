package com.multimediateam.parchisemocional.ui.ui.parchis

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.multimediateam.parchisemocional.interactor.IMainInteractor
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion

class ParchisViewModel() : ViewModel(), MainContract.MainPresenter {
    val mInteractor: MainContract.MainInteractor
    val mEmotion: MutableLiveData<Emotion> by lazy {
        MutableLiveData<Emotion>()
    }


    init {
        mInteractor = IMainInteractor()
    }

    override fun setEmotion(x: Float, y: Float) {
        mEmotion.value = Emotion.createEmotion( x, y)
    }

    override fun sendEmotion() {

    }
}
