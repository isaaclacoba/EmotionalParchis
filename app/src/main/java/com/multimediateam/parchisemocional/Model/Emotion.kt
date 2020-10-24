package com.multimediateam.parchisemocional.Model

data class Emotion(
    var feeling: Int = 0,
    var energy_: Int = 0
) {
    companion object Factory {
        public fun createEmotion(x: Float, y: Float): Emotion {
            //This will transform the data from 0-1000-ish range to 0-10(-5 to +5)
            val y_offset = -100
            val x_offset = 20
            val x_normalize_factor = 100
            val y_normalize_factor = -100

            var feeling = (((x + x_offset)/ x_normalize_factor)).toInt() - 5 // ToDO: fix
            var energy = (((y + y_offset) / y_normalize_factor)).toInt()  + 4  // ToDO: fix

            return Emotion(feeling, energy)
        }
    }
}