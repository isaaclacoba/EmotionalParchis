package com.multimediateam.parchisemocional.model

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class EmotionTest {
    private lateinit var mEmotion: Emotion

    @Before
    fun setUp() {
        mEmotion = Emotion.createEmotion(550f, 515f) //That is (feeling,energy) = (0,0)
    }

    @Test
    fun update() {
        //Check with the initial setUP configuration
        assertEquals(0, mEmotion.feeling_)
        assertEquals(0, mEmotion.energy_)

        //Update the emotion
        mEmotion.update(730f, 150f) // (feeling,energy) = (2,4)

        //Check with the new configuration
        assertEquals(2, mEmotion.feeling_)
        assertEquals(4, mEmotion.energy_)
    }

    @Test
    fun toEntity() {
        val emotionRow = mEmotion.toEntity()
        assertEquals(0, emotionRow.energy)
        assertEquals(0, emotionRow.feeling)
    }

    @Test
    fun getFeeling_() {
        assertEquals(0, mEmotion.feeling_)
    }

    @Test
    fun getEnergy_() {
        assertEquals(0, mEmotion.energy_)
    }

    @Test
    fun checkBoundValues() {
        //(1040, 50) => 5 feeling ,5 energy
        mEmotion.update(1040f, 50f)
        assertEquals(mEmotion.feeling_, 5)
        assertEquals(mEmotion.energy_, 5)


        //(1040, 1000) => 5 feeling, -5 energy
        mEmotion.update(1040f, 1000f)
        assertEquals( 5, mEmotion.feeling_)
        assertEquals(-5, mEmotion.energy_)

        //(70, 1000) => -5 feeling, -5 energy
        mEmotion.update(70f, 1000f)
        assertEquals( -5, mEmotion.feeling_)
        assertEquals(-5, mEmotion.energy_)

        //(70, 50) => -5 feeling, 5 energy
        mEmotion.update(70f, 50f)
        assertEquals( -5, mEmotion.feeling_)
        assertEquals(5, mEmotion.energy_)
    }
}