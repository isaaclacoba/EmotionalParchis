package com.multimediateam.parchisemocional.model

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class EmotionTest {
    private lateinit var mEmotion: Emotion

    @Before
    fun setUp() {
        mEmotion = Emotion.createEmotion(250f, 500f) //That is (feeling,energy) = (-3, +1)
    }

    @Test
    fun update() {
        //Check with the initial setUP configuration
        assertEquals(mEmotion.feeling_, -3)
        assertEquals(mEmotion.energy_,1)

        //Update the emotion
        mEmotion.update(550f, 600f) // (feeling,energy) = (0,0)

        //Check with the new configuration
        assert(mEmotion.energy_ == 0)
        assert(mEmotion.feeling_ == 0)
    }

    @Test
    fun toEntity() {
        val emotionRow = mEmotion.toEntity()
        assertEquals(emotionRow.energy, 1)
        assertEquals(emotionRow.feeling, -3)
    }

    @Test
    fun getFeeling_() {
        assertEquals(mEmotion.feeling_, -3)
    }

    @Test
    fun getEnergy_() {
        assertEquals(mEmotion.energy_,1)
    }

    @Test
    fun checkBoundValues() {
        //(1040, 100) => 5 feeling ,5 energy
        mEmotion.update(1040f, 100f)
        assertEquals(mEmotion.feeling_, 5)
        assertEquals(mEmotion.energy_, 5)


        //(1040, 1120) => 5 feeling, -5 energy
        mEmotion.update(1040f, 1120f)
        assertEquals(mEmotion.feeling_, 5)
        assertEquals(mEmotion.energy_, -5)

        //(60, 1120) => -5 feeling, -5 energy
        mEmotion.update(60f, 1120f)
        assertEquals(mEmotion.feeling_, -5)
        assertEquals(mEmotion.energy_, -5)

        //(70, 70) => 5 feeling, 5 energy
        mEmotion.update(70f, 70f)
        assertEquals(mEmotion.feeling_, -5)
        assertEquals(mEmotion.energy_, 5)
    }
}