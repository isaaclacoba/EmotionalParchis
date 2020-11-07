package com.multimediateam.parchisemocional.ui.ui.parchis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.multimediateam.parchisemocional.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParchisFragment : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
