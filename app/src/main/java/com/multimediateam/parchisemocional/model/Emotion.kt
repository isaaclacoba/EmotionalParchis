package com.multimediateam.parchisemocional.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Emotion(
    var feeling_: Int,
    var energy_: Int
) {
    private val TAG: String = "Emotion"

    public fun update(x: Float, y: Float) {
        val (feeling , energy) = coordinatesToEmotion(x, y)

        feeling_ = feeling
        energy_ = energy
    }


    public fun toEntity(): EmotionRow = EmotionRow(feeling_, energy_)

    companion object Factory {
        public fun coordinatesToEmotion(x: Float, y: Float): Pair<Int, Int> {
            //This will transform the data from 0-1000-ish range to 0-10(-5 to +5)
            val yOffset = -50
            val xOffset = 20
            val xNormalizeFactor = 100 // to convert values from 0,1000 to 0,10
            val yNormalizeFactor = -100 // same but y grows upside down x
            val xNormalizeValue = -5 //converts the final result from 0,10 to -5,+5
            val yNormalizeValue = +5 //converts the final result from 0,10 to -5,+5

            var feeling = (((x + xOffset)/ xNormalizeFactor)).toInt() + xNormalizeValue
            var energy = (((y + yOffset) / yNormalizeFactor)).toInt()  + yNormalizeValue

            return Pair(feeling, energy)
        }

        public fun createEmotion(x: Float, y: Float): Emotion {
            val (feeling, energy) = coordinatesToEmotion(x, y)
            return Emotion(feeling, energy)
        }
    }
}


fun List<Emotion>.toEntity() = map { it.toEntity() }

@Entity
data class EmotionRow(
    @ColumnInfo(name = "feeling")   val feeling: Int,
    @ColumnInfo(name = "energy") val energy: Int,
    @PrimaryKey @ColumnInfo(name = "timestamp") val timestamp: Long = System.currentTimeMillis()
    ) {
    public fun toDomain(): Emotion = Emotion(feeling, energy)
}

fun List<EmotionRow>.toDomain() = map { it.toDomain()}