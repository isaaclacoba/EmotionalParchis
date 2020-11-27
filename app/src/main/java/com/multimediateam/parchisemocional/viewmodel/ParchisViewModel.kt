package com.multimediateam.parchisemocional.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.di.IoDispatcher
import com.multimediateam.parchisemocional.di.ViewModelScope
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.model.Result
import kotlinx.coroutines.*
import org.jetbrains.annotations.TestOnly

class ParchisViewModel @ViewModelInject constructor(
    private var mInteractor: MainContract.MainInteractor,
    @IoDispatcher private var mIoDispatcher: CoroutineDispatcher,
    @Assisted private val mSavedStateHandle: SavedStateHandle):
    ViewModel(), MainContract.MainPresenter {

    private val TAG = "ParchisViewModel"

    var mEmotion: Emotion = Emotion.createEmotion(0f, 0f)
    val mResult = MutableLiveData<String>()
    val mEmotionList =
        MutableLiveData<List<Emotion>>(mutableListOf(Emotion.createEmotion(0f, 0f)))

    var coroutineScope = CoroutineScope(mIoDispatcher + SupervisorJob())


    override fun setEmotion(x: Float, y: Float) {
        mEmotion = Emotion.createEmotion( x, y)
    }

    override fun sendEmotion() {
        coroutineScope.launch(mIoDispatcher) {
            val result = mInteractor.addEmotion(mEmotion)
            val msg = result?.message
            mResult.postValue(msg)

            //Log.i(TAG, "result: ${mResult.value}")
        }
    }

    override fun getEmotionsAsync() {
        viewModelScope.launch {
            val result = withContext(mIoDispatcher) {
                mInteractor.getEmotions()
            }

            mEmotionList.value = result
        }
    }
}
