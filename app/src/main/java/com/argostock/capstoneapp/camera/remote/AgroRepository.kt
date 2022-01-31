package com.argostock.capstoneapp.camera.remote

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import com.argostock.capstoneapp.camera.local.LocalDataSource
import com.argostock.capstoneapp.camera.local.entitiy.AgroEntity
import com.argostock.capstoneapp.camera.remote.network.ApiResponse
import com.argostock.capstoneapp.camera.remote.network.UploadRequest
import com.argostock.capstoneapp.camera.remote.response.AgroResponse
import com.argostock.capstoneapp.camera.source.NetworkBoundResource
import com.argostock.capstoneapp.camera.source.Resource
import com.argostock.capstoneapp.camera.utils.AppExecutors
import java.io.File

class AgroRepository private constructor(private val dataRemote: RemoteDataSource,
    private val srcLocalData: LocalDataSource, private val Executors: AppExecutors) : AgroDataSource {

    companion object {
        @Volatile
        private var i: AgroRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): AgroRepository =
            i ?: synchronized(this) {
                i ?: AgroRepository(remoteData, localData, appExecutors).apply {
                    i = this
                }
            }
    }
    override fun getResult(context: Context, file: File, body: UploadRequest): LiveData<Resource<AgroEntity>> {
        return object : NetworkBoundResource<AgroEntity, AgroResponse>(Executors){
            override fun loadFromDB(): LiveData<AgroEntity> =
                srcLocalData.getResult(file.name)

            override fun shouldFetch(data: AgroEntity?): Boolean =
                data?.id == null

            override fun createCall(): LiveData<ApiResponse<AgroResponse>> =
                dataRemote.getPredictionResult(context, file, body)

            override fun saveCallResult(data: AgroResponse) {
                val uri = Uri.fromFile(file)
                val result = AgroEntity(
                    id = file.name,
                    file = data.file.toString(),
                    result = data.result,
                )
                srcLocalData.insertResult(result)
            }
        }.asLiveData()
    }
}