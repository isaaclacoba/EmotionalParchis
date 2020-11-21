package com.multimediateam.parchisemocional.ui.plots

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import com.multimediateam.parchisemocional.R
import com.multimediateam.parchisemocional.model.Emotion
import com.multimediateam.parchisemocional.presenter.ParchisViewModel
import com.multimediateam.parchisemocional.util.Color.BLUE_PARCHIS
import com.multimediateam.parchisemocional.util.Color.GREEN_PARCHIS
import com.multimediateam.parchisemocional.util.Color.RED_PARCHIS
import com.multimediateam.parchisemocional.util.Color.YELLOW_PARCHIS
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.plot_fragment.*

@AndroidEntryPoint
class PlotFragment : Fragment(), OnChartValueSelectedListener {
    private val tfRegular by lazy {
        context?.let { ResourcesCompat.getFont(it, R.font.open_sans_regular) };
    }

    private val tfLight by lazy {
        context?.let { ResourcesCompat.getFont(it, R.font.open_sans_light) };
    }

    companion object {
        fun newInstance() = PlotFragment()
    }

    val viewModel: ParchisViewModel by viewModels()

    private val emotionLabel = arrayOf(
        "Anger", "Apathy", "Euphoria", "Relax")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.plot_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emotion_pie_chart.setUsePercentValues(true)
        emotion_pie_chart.description.isEnabled = false
        emotion_pie_chart.setExtraOffsets(5f, 10f, 5f, 5f)

        emotion_pie_chart.dragDecelerationFrictionCoef = 0.95f

        emotion_pie_chart.setCenterTextTypeface(tfLight)
        emotion_pie_chart.centerText = generateCenterSpannableText()

        emotion_pie_chart.isDrawHoleEnabled = true
        emotion_pie_chart.setHoleColor(Color.WHITE)

        emotion_pie_chart.setTransparentCircleColor(Color.WHITE)
        emotion_pie_chart.setTransparentCircleAlpha(110)

        emotion_pie_chart.holeRadius = 58f
        emotion_pie_chart.transparentCircleRadius = 61f

        emotion_pie_chart.setDrawCenterText(true)

        emotion_pie_chart.rotationAngle = 0f
        // enable rotation of the emotion_pie_chart by touch
        // enable rotation of the emotion_pie_chart by touch
        emotion_pie_chart.isRotationEnabled = true
        emotion_pie_chart.isHighlightPerTapEnabled = true


        // add a selection listener
        emotion_pie_chart.setOnChartValueSelectedListener(this)

        emotion_pie_chart.animateY(1400, Easing.EaseInOutQuad)

        val l: Legend = emotion_pie_chart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // entry label styling
        emotion_pie_chart.setEntryLabelColor(Color.WHITE)
        emotion_pie_chart.setEntryLabelTypeface(tfRegular)
        emotion_pie_chart.setEntryLabelTextSize(13f)

        viewModel.mEmotionList.observe(viewLifecycleOwner, Observer { emotionList ->
            setPieData(emotionList)
        })

        viewModel.getEmotionsAsync()
    }

    private fun generateCenterSpannableText(): SpannableString? {
        val s = SpannableString("Emotional Parchis\nResults")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 17, 0)
        return s
    }


    private fun getDataSet(emotions: List<Emotion>): PieDataSet {
        val entries = mutableListOf<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        var emotionCounter = mutableListOf<Int>(0, 0, 0, 0)
        for (emotion in emotions) {
            if (emotion.energy_ > 0) {
                if (emotion.feeling_ > 0) {
                    emotionCounter[2]++
                } else {
                    emotionCounter[0]++
                }
            } else {
                if (emotion.feeling_ > 0) {
                    emotionCounter[3]++
                } else {
                    emotionCounter[1]++

                }
            }
        }

        for ((i, value) in emotionCounter.withIndex()) {
            entries.add(
                PieEntry(
                    value.toFloat(),
                    emotionLabel[i],
                    resources.getDrawable(R.drawable.star)
                )
            )
        }

        val dataSet = PieDataSet(entries, "Emotional Parchís")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0F, 40F)
        dataSet.selectionShift = 5f

        return dataSet
    }

    private fun setPieData(emotions: List<Emotion>) {
        val dataSet = getDataSet(emotions)

        // add a lot of colors
        val colors = mutableListOf<Int>(RED_PARCHIS,
            BLUE_PARCHIS, YELLOW_PARCHIS, GREEN_PARCHIS)

        dataSet.colors = colors
        dataSet.selectionShift = 0f;

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(20f)
        data.setValueTextColor(Color.WHITE)
        data.setValueTypeface(tfLight)
        emotion_pie_chart.data = data

        // undo all highlights
        emotion_pie_chart.highlightValues(null)
        emotion_pie_chart.invalidate()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onNothingSelected() {
        //TODO("Not yet implemented")

    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e == null) return
        Log.i(
            "VAL SELECTED",
            "Value: " + e.y + ", index: " + h!!.x
                    + ", DataSet index: " + h.dataSetIndex
        )
    }

}