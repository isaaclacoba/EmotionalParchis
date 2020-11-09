package com.multimediateam.parchisemocional

import com.multimediateam.parchisemocional.data.DatabaseHelper
import com.multimediateam.parchisemocional.interactor.IMainInteractor
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var mInteractor: MainContract.MainInteractor

    @Mock private lateinit var databaseHelper: DatabaseHelper


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
