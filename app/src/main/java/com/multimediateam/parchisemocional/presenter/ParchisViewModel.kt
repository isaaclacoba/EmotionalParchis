package com.multimediateam.parchisemocional.presenter

import android.content.Context
import android.util.Log
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

    private val TAG = "ParchisViewModel"

    val mResult = MutableLiveData<String>()
    val mEmotion: MutableLiveData<Emotion> =
        MutableLiveData<Emotion>(Emotion.createEmotion(0f, 0f))

    override fun setEmotion(x: Float, y: Float) {
        mEmotion.value = Emotion.createEmotion( x, y)
    }

    override fun sendEmotion() {
        val emotion = mEmotion.value!!

        GlobalScope.launch {
            val result = mInteractor.addEmotion(emotion)
            Log.i(TAG, "result: ${result.toString()}")
            mResult.postValue(result.message)
        }
    }
}
