package com.multimediateam.parchisemocional.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.multimediateam.parchisemocional.model.EmotionRow

@Database(entities = [EmotionRow::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emotionDao(): EmotionDao
}