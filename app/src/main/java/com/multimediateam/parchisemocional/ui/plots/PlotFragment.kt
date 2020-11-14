package com.multimediateam.parchisemocional.ui.plots

import android.graphics.Color
import android.graphics.Typeface
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
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.multimediateam.parchisemocional.R
import kotlinx.android.synthetic.main.plot_fragment.*
import java.util.ArrayList

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


    private val parties = arrayOf(
        "Anger", "Apathy", "Euphoria", "Relax")

    private lateinit var viewModel: PlotViewModel

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

        // entry label styling
        emotion_pie_chart.setEntryLabelColor(Color.GRAY)
        emotion_pie_chart.setEntryLabelTypeface(tfRegular)
        emotion_pie_chart.setEntryLabelTextSize(12f)
        setData(4, 5f)

    }

    private fun generateCenterSpannableText(): SpannableString? {
        //val s = SpannableString("EmotionParchis\nEmotion Pie")
        val s = SpannableString("Emotional Parchis\nResults")
        s.setSpan(RelativeSizeSpan(1.7f), 0, 17, 0)
        return s
    }

    private fun setData(count: Int, range: Float) {
        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(
                PieEntry(
                    (Math.random() * range + range / 5).toFloat(),
                    parties.get(i % parties.size),
                    resources.getDrawable(R.drawable.star)
                )
            )
        }
        val dataSet = PieDataSet(entries, "Emotional Parchís")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0F, 40F)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        data.setValueTypeface(tfLight)
        emotion_pie_chart.data = data

        // undo all highlights
        emotion_pie_chart.highlightValues(null)
        emotion_pie_chart.invalidate()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlotViewModel::class.java)
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
                    + ", DataSet index: " + h!!.dataSetIndex
        )
    }

}