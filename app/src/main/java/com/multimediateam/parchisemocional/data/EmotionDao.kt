package com.multimediateam.parchisemocional.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.multimediateam.parchisemocional.model.EmotionRow

@Dao
interface EmotionDao {
    @Query("SELECT * FROM emotionrow")
    fun getAll(): List<EmotionRow>

    @Insert
    fun insertAll(vararg emotions: EmotionRow)

    @Delete
    fun delete(emotion: EmotionRow)
}