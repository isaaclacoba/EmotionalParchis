package com.multimediateam.parchisemocional.ui.ui.parchis

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.multimediateam.parchisemocional.interactor.IMainInteractor
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.model.Emotion
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ParchisViewModel @ViewModelInject constructor(
    private var mInteractor: MainContract.MainInteractor,
    @Assisted private val savedStateHandle: SavedStateHandle):
    ViewModel(), MainContract.MainPresenter {

    val mEmotion: MutableLiveData<Emotion> =
        MutableLiveData<Emotion>(Emotion.createEmotion(0f, 0f))

    override fun setEmotion(x: Float, y: Float) {
        mEmotion.value = Emotion.createEmotion( x, y)

    }

    override fun sendEmotion() {
        val emotion = mEmotion.value!!

        GlobalScope.launch {
            mInteractor.addEmotion(emotion)
        }
    }
}
