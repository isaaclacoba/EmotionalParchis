package com.multimediateam.parchisemocional.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.multimediateam.parchisemocional.R
import com.multimediateam.parchisemocional.R.*
import com.multimediateam.parchisemocional.ui.ui.parchis.ParchisFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParchisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.parchis_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(id.container, ParchisFragment.newInstance())
                .commitNow()
        }
    }
}
