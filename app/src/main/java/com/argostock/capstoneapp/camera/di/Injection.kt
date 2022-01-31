package com.argostock.capstoneapp.camera.di

import android.content.Context
import com.argostock.capstoneapp.camera.local.LocalDataSource
import com.argostock.capstoneapp.camera.local.room.AgroDatabase
import com.argostock.capstoneapp.camera.remote.AgroRepository
import com.argostock.capstoneapp.camera.remote.RemoteDataSource
import com.argostock.capstoneapp.camera.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): AgroRepository {
        val database = AgroDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.predictDao())
        val appExecutors = AppExecutors()
        return AgroRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}