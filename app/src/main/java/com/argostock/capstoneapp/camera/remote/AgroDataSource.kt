package com.argostock.capstoneapp.camera.remote

import android.content.Context
import androidx.lifecycle.LiveData
import com.argostock.capstoneapp.camera.local.entitiy.AgroEntity
import com.argostock.capstoneapp.camera.remote.network.UploadRequest
import com.argostock.capstoneapp.camera.source.Resource
import java.io.File

interface AgroDataSource {
    fun getResult(context: Context, file: File, body: UploadRequest): LiveData<Resource<AgroEntity>>
}