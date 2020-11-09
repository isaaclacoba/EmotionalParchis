package com.multimediateam.parchisemocional.ui.ui.parchis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.multimediateam.parchisemocional.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class ParchisFragment : Fragment() {
    private val TAG: String = "ParchisFragment"

    companion object {
        fun newInstance() = ParchisFragment()
    }

    private val viewModel: ParchisViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.parchis_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            viewModel.sendEmotion()
        }
    }

    private fun drawCircle(event: MotionEvent) {
        var duration = 250L
        if (parchis_point_iv.visibility != View.VISIBLE) {
            parchis_point_iv.visibility = View.VISIBLE
        }

        val xOffset = 40
        val yOffset = -300
        parchis_point_iv.animate()
            .x(event.x - xOffset)
            .y(event.y - yOffset)
            .setDuration(duration)
            .start()

        viewModel.setEmotion(event.x, event.y)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
