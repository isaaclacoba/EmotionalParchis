package com.multimediateam.parchisemocional.ui.parchis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.multimediateam.parchisemocional.R
import com.multimediateam.parchisemocional.R.*
import com.multimediateam.parchisemocional.ui.parchis.ParchisFragment
import com.multimediateam.parchisemocional.ui.plots.PlotFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.parchis_activity.*

@AndroidEntryPoint
class ParchisActivity : AppCompatActivity() {

    private val TAG = "ParchisActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.parchis_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(id.container,
                    ParchisFragment.newInstance()
                )
                .commitNow()
        }
        initViews()
    }

    private fun initViews() {
        bottom_navigation
            .setOnNavigationItemSelectedListener { item-> onNavigationItemSelected(item) }
        bottom_navigation
            .setOnNavigationItemReselectedListener { item -> onNavigationItemReselected(item) }
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.item_emotion_panel -> {
                supportFragmentManager.beginTransaction()
                    .replace(
                        id.container,
                        ParchisFragment.newInstance()
                    )
                    .commitNow()
                true
            }
            R.id.item_pie_chart_panel -> {
                supportFragmentManager.beginTransaction()
                    .replace(
                        id.container,
                        PlotFragment.newInstance()
                    )
                    .commitNow()
                true
            }
            else -> {
                Log.d(TAG, "onNavigationItemSelected")
                false
            }
        }

    fun onNavigationItemReselected(item: MenuItem) {
        Log.d(TAG, "onNavigationItemReselected")
    }
}
