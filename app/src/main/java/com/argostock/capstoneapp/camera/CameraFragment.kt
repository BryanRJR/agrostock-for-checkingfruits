package com.argostock.capstoneapp.camera

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.argostock.capstoneapp.R
import com.argostock.capstoneapp.camera.remote.network.UploadRequest
import com.argostock.capstoneapp.camera.utils.getFileName
import com.argostock.capstoneapp.camera.utils.snackbar
import com.argostock.capstoneapp.databinding.FragmentCameraBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class CameraFragment : Fragment(), UploadRequest.UploadCallback {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding as FragmentCameraBinding
    private val cameraViewModel by lazy {
        ViewModelProvider(this, FactoryViewModel.getInstance(requireActivity())).get(CameraViewModel::class.java)
    }
    private var ImageUri: Uri? = null
    //private lateinit var photoFile: File
    private lateinit var file: File
    private lateinit var body: UploadRequest

    // companion object {
    //private const val FILE_NAME = "photo.jpg"
    // }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View { _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonCapture.setOnClickListener {
                val directoryIntent = Intent(Intent.ACTION_PICK)
                directoryIntent.type = "image/"
                startForDirectory.launch(directoryIntent)
            }

            buttonPredict.setOnClickListener {
                buttonPredict.visibility = View.GONE
                uploadImage()
                context?.let { getDataUserFromApi(it) }
            }
        }
    }

    private val startForDirectory =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.apply {
                    imgPreviewSample.visibility = View.GONE
                    imagePreview.visibility = View.VISIBLE
                    ImageUri = result.data?.data
                    imagePreview.setImageURI(ImageUri)
                    buttonPredict.visibility = View.VISIBLE
                }
            }
        }

    private fun getDataUserFromApi(context: Context) {
        cameraViewModel.getPredictionResult(context, file, body).observe(viewLifecycleOwner, { fruit ->
            binding.progBar.progress = 100
            val result = fruit.data

            val text1 = result?.result

            val text2: String = result?.result.toString()
            if (text1 == null) {
                binding.tvResultPredict.text = getString(R.string.loading_server)
            } else {
                binding.tvResultPredict.text =
                    resources.getString(R.string.result_predict, text1.toString(), text2)
            }
        })
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun uploadImage() {
        if (ImageUri == null) {
            binding.layoutRoot.snackbar("Select Image")
            return
        }
        val resolver = requireActivity().contentResolver
        val cacheDir = activity?.externalCacheDir

        val parcelFileDescriptor =
            resolver.openFileDescriptor(ImageUri!!, "r", null) ?: return


        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        file = File(cacheDir, resolver.getFileName(ImageUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        binding.progBar.progress = 0

        body = UploadRequest(file, "image", this)
    }

    override fun onProgressUpdate(percentage: Int) {
        binding.progBar.progress = percentage
    }

    private fun getImageUriFromBitmap(bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, bytes)
        val resolver = requireActivity().contentResolver
        val path = MediaStore.Images.Media.insertImage(
            resolver,
            bitmap,
            System.currentTimeMillis().toString(),
            null
        )
        return Uri.parse(path.toString())
    }

}