package com.argostock.capstoneapp.camera

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.argostock.capstoneapp.camera.local.entitiy.AgroEntity
import com.argostock.capstoneapp.camera.remote.AgroRepository
import com.argostock.capstoneapp.camera.remote.network.UploadRequest
import com.argostock.capstoneapp.camera.remote.response.AgroResponse
import com.argostock.capstoneapp.camera.source.Resource
import java.io.File

class CameraViewModel(private val pred_Repository: AgroRepository) : ViewModel() {

    private val listDetails = MutableLiveData<AgroResponse>()

    fun getPredictionResult(context: Context, file: File, body: UploadRequest): LiveData<Resource<AgroEntity>> =
        pred_Repository.getResult(context, file, body)
}