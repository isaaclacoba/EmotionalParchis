package com.multimediateam.parchisemocional.Model

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Emotion(
    var timestamp_: Long,
    var feeling_: Int,
    var energy_: Int
) {
    private val TAG: String = "Emotion"

    public fun update(x: Float, y: Float) {
        val (timestamp, feeling , energy) = coordinatesToEmotion(x, y)

        timestamp_ = timestamp
        feeling_ = feeling
        energy_ = energy
    }


    public fun toEntity(): EmotionRow = EmotionRow(timestamp_, feeling_, energy_)

    companion object Factory {
        public fun coordinatesToEmotion(x: Float, y: Float): Triple<Long, Int, Int> {
            val y_offset = -50
            val x_offset = 20
            val x_normalize_factor = 100
            val y_normalize_factor = -100

            var feeling = (((x + x_offset)/ x_normalize_factor)).toInt() - 5 // ToDO: fix
            var energy = (((y + y_offset) / y_normalize_factor)).toInt()  + 5  // ToDO: fix
            /*
            //This will transform the data from 0-1000-ish range to 0-10(-5 to +5)
            val yOffset = -100
            val xOffset = 20
            val xNormalizeFactor = 100 // to convert values from 0,1000 to 0,10
            val yNormalizeFactor = -100 // same but y grows upside down x
            val xNormalizeValue = -5 //converts the final result from 0,10 to -5,+5
            val yNormalizeValue = 4 //converts the final result from 0,10 to -5,+5

            var feeling = (((x + xOffset)/ xNormalizeFactor)).toInt() - xNormalizeValue
            var energy = (((y + yOffset) / yNormalizeFactor)).toInt()  + yNormalizeValue

             */
            val timestamp = System.currentTimeMillis()

            return Triple(timestamp, feeling, energy)
        }

        public fun createEmotion(x: Float, y: Float): Emotion {
            val (timestamp, feeling, energy) = coordinatesToEmotion(x, y)
            return Emotion(timestamp, feeling, energy)
        }
    }
}

@Entity
data class EmotionRow(
    @PrimaryKey @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "feeling")   val feeling: Int,
    @ColumnInfo(name = "energy") val energy: Int
) {
    public fun toEmotion(): Emotion = Emotion(timestamp, feeling, energy)
}