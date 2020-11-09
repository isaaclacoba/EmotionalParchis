package com.multimediateam.parchisemocional.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.multimediateam.parchisemocional.MainContract
import com.multimediateam.parchisemocional.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    @Inject lateinit var presenter: MainContract.MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        parchis_emotional_iv.setOnTouchListener { _, event ->
            Log.e(TAG,"event ${event.x}: ${event.y}")

            if (event.action == (MotionEvent.ACTION_MOVE or MotionEvent.ACTION_DOWN)) {
                drawCircle(event)
            }

            true
        }

        send_feeling_btn.setOnClickListener {
            presenter.sendEmotion()
        }
    }

    private fun drawCircle(event: MotionEvent) {
        var duration = 250L
        if (parchis_point_iv.visibility != View.VISIBLE) {
            parchis_point_iv.visibility = View.VISIBLE
        }

        val xOffset = 40
        val yOffset = 30
        parchis_point_iv.animate()
            .x(event.x - xOffset)
            .y(event.y - yOffset)
            .setDuration(duration)
            .start()

        presenter.setEmotion(event.x, event.y)

    }

}
