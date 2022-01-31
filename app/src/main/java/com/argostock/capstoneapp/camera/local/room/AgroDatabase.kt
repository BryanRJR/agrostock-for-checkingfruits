package com.argostock.capstoneapp.camera.local.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.argostock.capstoneapp.camera.local.entitiy.AgroEntity

@Database(entities = [AgroEntity::class], version = 2, exportSchema = false)
abstract class AgroDatabase : RoomDatabase() {
    abstract fun predictDao(): AgroDao

    companion object {
        @Volatile
        private var INSTANCE: AgroDatabase? = null

        fun getInstance(context: Context): AgroDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AgroDatabase::class.java,
                    "agrostock_database"
                ).fallbackToDestructiveMigration().build().apply {
                    INSTANCE = this
                }
            }
    }
}