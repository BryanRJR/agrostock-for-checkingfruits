package com.argostock.capstoneapp.camera.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.argostock.capstoneapp.camera.local.entitiy.AgroEntity

@Dao
interface AgroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = AgroEntity::class)
    fun insertResult(prediction: AgroEntity)

    @Query("SELECT * FROM agrostock_result_tabel WHERE id = :id")
    fun getResult(id: String): LiveData<AgroEntity>
}