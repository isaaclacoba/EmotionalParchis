package com.multimediateam.parchisemocional.Model

data class Emotion(
    var feeling: Int = 0,
    var energy_: Int = 0
) {
    companion object Factory {
        public fun createEmotion(x: Float, y: Float): Emotion {
            var feeling = ((x % 11)).toInt() // ToDO: fix
            var energy = ((y % 11)).toInt() // ToDO: fix

            return Emotion(feeling, energy)
        }
    }
}