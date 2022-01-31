package com.argostock.capstoneapp.camera.local

import androidx.lifecycle.LiveData
import com.argostock.capstoneapp.camera.local.entitiy.AgroEntity
import com.argostock.capstoneapp.camera.local.room.AgroDao

class LocalDataSource private constructor(private val prd_Dao: AgroDao) {
    // local data
    fun insertResult(data: AgroEntity) {
        prd_Dao.insertResult(data)
    }
    fun getResult(image: String): LiveData<AgroEntity> = prd_Dao.getResult(image)

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(predictDao: AgroDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(predictDao)
    }
}