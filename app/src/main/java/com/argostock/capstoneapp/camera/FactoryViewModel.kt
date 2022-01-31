package com.argostock.capstoneapp.camera

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.argostock.capstoneapp.camera.di.Injection
import com.argostock.capstoneapp.camera.remote.AgroRepository

class FactoryViewModel private constructor(private val pred_Repository: AgroRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CameraViewModel::class.java) -> {
                CameraViewModel(pred_Repository) as T
            }
            modelClass.isAssignableFrom(CameraViewModel::class.java) -> {
                CameraViewModel(pred_Repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        @Volatile
        private var instance: FactoryViewModel? = null

        fun getInstance(context: Context): FactoryViewModel =
            instance ?: synchronized(this) {
                instance ?: FactoryViewModel(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

}