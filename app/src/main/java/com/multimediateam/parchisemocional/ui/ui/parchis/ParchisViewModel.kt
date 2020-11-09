package com.multimediateam.parchisemocional.ui.ui.parchis

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.multimediateam.parchisemocional.interactor.IMainInteractor
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import javax.inject.Inject

class ParchisViewModel(var mInteractor: MainContract.MainInteractor):
    ViewModel(), MainContract.MainPresenter {

    val mEmotion: MutableLiveData<Emotion> by lazy {
        MutableLiveData<Emotion>()
    }

    override fun setEmotion(x: Float, y: Float) {
        mEmotion.value = Emotion.createEmotion( x, y)
    }

    override fun sendEmotion() {}
}
